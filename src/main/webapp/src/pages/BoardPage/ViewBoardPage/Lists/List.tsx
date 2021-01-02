import { Droppable } from 'react-beautiful-dnd'
import { Draggable } from 'react-beautiful-dnd'
import React from 'react'

class List extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            issues: [
                { title: "test1", id: Math.floor(Math.random() * Math.floor(100)).toString()},
                { title: "test2", id: Math.floor(Math.random() * Math.floor(100)).toString()},
            ]
        }
    }

    render() {
        return (
            <Droppable key={this.props.boardColumn.label} droppableId={this.props.boardColumn.label}>
                {(provided, snapshot) => (
                    <div
                        ref={provided.innerRef}
                        style={{ backgroundColor: snapshot.isDraggingOver ? 'blue' : 'grey', width: 250 }}
                        {...provided.droppableProps}
                    >
                        <h2>{this.props.boardColumn.label}</h2>
                        {provided.placeholder}

                        {this.state.issues.map((issue, index) =>
                            <Draggable
                                key={issue.id}
                                draggableId={issue.id}
                                index={index}
                            >
                                {(provided, snapshot) => (
                                    <div
                                        ref={provided.innerRef}
                                        {...provided.draggableProps}
                                        {...provided.dragHandleProps}>
                                        {issue.title}
                                    </div>
                                )}
                            </Draggable>
                        )}
                    </div>
                )}
            </Droppable>
        )
    }
}

export { List }