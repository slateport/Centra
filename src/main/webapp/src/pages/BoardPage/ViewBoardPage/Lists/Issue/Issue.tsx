import React from 'react'
import { Draggable } from 'react-beautiful-dnd'
import {issueHelper} from "../../../../../helpers";
import { IssueLink, IssueDiv, Title, Bottom, Assignees, AssigneeAvatar } from './Styles';
import {Link, Tooltip} from "@material-ui/core";
import {issue, project, user} from "../../../../../services";
import { iconMap as typeIconMap } from '../../../../ViewIssuePage/components/IssueTypePicker';
import {priorityMap} from "../../../../ViewIssuePage/components/IssuePriorityPicker";

interface IIssueProps {
    issue: any,
    index: number
}

const typePromise = (id) => issue.getIssueTypeById(id)
const userPromise = (userId) => user.getUserLite(userId)
const priorityPromise = (id) => project.getPriorityById(id)

class Issue extends React.Component<IIssueProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            issueType: {},
            issuePriority: {},
            assignee: {}
        }
    }

    componentDidMount() {
        typePromise(this.props.issue.issueTypeId)
            .then(issueType => this.setState({ issueType }))
        priorityPromise(this.props.issue.issuePriorityId)
            .then(issuePriority => this.setState({ issuePriority }))

        if (this.props.issue.assigneeId){
            userPromise(this.props.issue.assigneeId)
                .then(assignee => this.setState({ assignee }))
        }
    }

    render() {
        return (
            <Draggable
                key={this.props.issue.id}
                draggableId={this.props.issue.id}
                index={this.props.index}
            >
                {(provided, snapshot) => (
                    <IssueLink
                        to={`/browse/${issueHelper.buildExternalKey(this.props.issue)}`}
                        ref={provided.innerRef}
                        data-testid="list-issue"
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}
                        component={Link}
                    >
                        <IssueDiv isBeingDragged={snapshot.isDragging && !snapshot.isDropAnimating}>
                            <small>{issueHelper.buildExternalKey(this.props.issue)}</small>
                            <br />
                            <Title>{this.props.issue.title}</Title>
                            <Bottom>
                                <div>
                                    <Tooltip title={this.state.issueType.label}>
                                        {typeIconMap[this.state.issueType.icon]()}
                                    </Tooltip>
                                    <Tooltip title={this.state.issuePriority.label}>
                                        {typeof priorityMap[(this.state.issuePriority || {}).icon] === 'function' ? priorityMap[(this.state.issuePriority || {}).icon](this.state.issuePriority) : null}
                                    </Tooltip>
                                </div>
                                <Assignees>
                                    {this.props.issue.assigneeId &&
                                        <Tooltip title={this.state.assignee.displayName}>
                                            <AssigneeAvatar />
                                        </Tooltip>
                                    }
                                </Assignees>
                            </Bottom>
                        </IssueDiv>
                    </IssueLink>
                )}
            </Draggable>
        )
    }
}

export { Issue }
