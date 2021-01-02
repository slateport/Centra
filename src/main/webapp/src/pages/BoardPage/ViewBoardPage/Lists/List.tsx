import { Droppable } from 'react-beautiful-dnd'
import React from 'react'
import styled from "styled-components";
import {Issue} from "./Issue/Issue";

export const ListDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 5px;
  min-height: 400px;
  min-width: 25%;
  border-radius: 3px;
`;

export const Title = styled.div`
  padding: 13px 10px 17px;
  text-transform: uppercase;
  font-size: 12.5px;
  font-weight: 700;
  color: #5E6C84;
`;

export const IssuesCount = styled.span`
  text-transform: lowercase;
  font-size: 13px;
`;

export const Issues = styled.div`
  height: 100%;
  padding: 0 5px;
  background: #fff;
  padding: 10px;
`;

function objectsMatch(obj1, obj2) {
    if (Object.keys(obj1).length !== Object.keys(obj2).length) {
        return false;
    }

    return Object.entries(obj1).every(([key, value]) => {
        return obj2[key] === value;
    });
}

const statusFiltered = (workflowStates: any[], issues: any[]) => {
    return issues.filter(issue => {
        return workflowStates.some(state => objectsMatch(state, issue.workflowState));
    })
}

class List extends React.Component<{issues: any[], boardColumn: any}, any> {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Droppable key={this.props.boardColumn.label} droppableId={this.props.boardColumn.label}>
                {(provided) => (
                   <ListDiv>
                       <ListDiv>
                           <Title>
                               {`${this.props.boardColumn.label} `}
                               <IssuesCount>
                                   {statusFiltered(this.props.boardColumn.workflowStates, this.props.issues).length}
                               </IssuesCount>
                           </Title>
                           <Issues
                               {...provided.droppableProps}
                               ref={provided.innerRef}
                               data-testid={`board-list:${status}`}
                           >
                               {statusFiltered(this.props.boardColumn.workflowStates, this.props.issues).map((issue, index) =>
                                <Issue issue={issue} index={index} />
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