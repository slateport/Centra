import React from 'react'
import { Draggable } from 'react-beautiful-dnd'
import {issueHelper} from "../../../../../helpers";
import { IssueLink, IssueDiv, Title, Bottom } from './Styles';

interface IIssueProps {
    issue: any,
    index: number
}

class Issue extends React.Component<IIssueProps, any> {
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
                    >
                        <IssueDiv isBeingDragged={snapshot.isDragging && !snapshot.isDropAnimating}>
                            <Title>{this.props.issue.title}</Title>
                            <Bottom>
                                {issueHelper.buildExternalKey(this.props.issue)}
                            </Bottom>
                        </IssueDiv>
                    </IssueLink>
                )}
            </Draggable>
        )
    }
}

export { Issue }
