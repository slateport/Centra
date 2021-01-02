import { Droppable } from 'react-beautiful-dnd'
import { Draggable } from 'react-beautiful-dnd'
import React from 'react'
import styled from "styled-components";

export const ListDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 5px;
  min-height: 400px;
  min-width: 25%;
  border-radius: 3px;
  background: #fff;
`;

export const Title = styled.div`
  padding: 13px 10px 17px;
  text-transform: uppercase;
  font-size: 12.5px;
`;

export const IssuesCount = styled.span`
  text-transform: lowercase;
  font-size: 13px;
`;

export const Issues = styled.div`
  height: 100%;
  padding: 0 5px;
`;


const formatIssuesCount = (allListIssues, filteredListIssues) => {
    if (allListIssues.length !== filteredListIssues.length) {
        return `${filteredListIssues.length} of ${allListIssues.length}`;
    }
    return allListIssues.length;
};


class List extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            issues: [
                { title: Math.floor(Math.random() * Math.floor(100)).toString(), id: Math.floor(Math.random() * Math.floor(100)).toString()},
                { title: Math.floor(Math.random() * Math.floor(100)).toString(), id: Math.floor(Math.random() * Math.floor(100)).toString()},
            ]
        }
    }

    render() {
        return (
            <Droppable key={this.props.boardColumn.label} droppableId={this.props.boardColumn.label}>
                {(provided) => (
                   <ListDiv>
                       <ListDiv>
                           <Title>
                               {`${this.props.boardColumn.label} `}
                               <IssuesCount>{formatIssuesCount(this.state.issues, this.state.issues)}</IssuesCount>
                           </Title>
                           <Issues
                               {...provided.droppableProps}
                               ref={provided.innerRef}
                               data-testid={`board-list:${status}`}
                           >
                               {this.state.issues.map((issue, index) =>
                                   <Draggable
                                       key={issue.id}
                                       draggableId={issue.id}
                                       index={index}
                                   >
                                       {(provided) => (
                                           <div
                                               ref={provided.innerRef}
                                               {...provided.draggableProps}
                                               {...provided.dragHandleProps}>
                                               {issue.title}
                                           </div>
                                       )}
                                   </Draggable>
                               )}
                               {provided.placeholder}
                           </Issues>
                       </ListDiv>
                   </ListDiv>
                )}
            </Droppable>
        )
    }
}

export { List }