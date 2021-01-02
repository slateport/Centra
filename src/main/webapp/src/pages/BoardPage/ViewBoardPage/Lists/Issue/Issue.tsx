import React from 'react'
import { Draggable } from 'react-beautiful-dnd'
import {issueHelper} from "../../../../../helpers";

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
                {(provided) => (
                    <div
                        ref={provided.innerRef}
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}>
                        {issueHelper.buildExternalKey(this.props.issue)}: {this.props.issue.title}
                    </div>
                )}
            </Draggable>
        )
    }
}

export { Issue }
