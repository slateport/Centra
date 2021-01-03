import React from 'react'
import { Draggable } from 'react-beautiful-dnd'
import {issueHelper} from "../../../../../helpers";
import { IssueLink, IssueDiv, Title, Bottom } from './Styles';
import {Card, Link} from "@material-ui/core";
import {issue, project} from "../../../../../services";
import { iconMap as typeIconMap } from '../../../../ViewIssuePage/components/IssueTypePicker';
import {priorityMap} from "../../../../ViewIssuePage/components/IssuePriorityPicker";

interface IIssueProps {
    issue: any,
    index: number
}

const typePromise = (id) => issue.getIssueTypeById(id)
const priorityPromise = (id) => project.getPriorityById(id)

class Issue extends React.Component<IIssueProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            issueType: {},
            issuePriority: {}
        }
    }

    componentDidMount() {
        typePromise(this.props.issue.issueTypeId)
            .then(issueType => this.setState({ issueType }))
        priorityPromise(this.props.issue.issuePriorityId)
            .then(issuePriority => this.setState({ issuePriority }))
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
                                {typeIconMap[this.state.issueType.icon]()}
                                {typeof priorityMap[(this.state.issuePriority || {}).icon] === 'function' ? priorityMap[(this.state.issuePriority || {}).icon](this.state.issuePriority) : null}
                            </Bottom>
                        </IssueDiv>
                    </IssueLink>
                )}
            </Draggable>
        )
    }
}

export { Issue }
