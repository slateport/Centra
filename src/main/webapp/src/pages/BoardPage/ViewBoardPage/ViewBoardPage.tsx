import React from 'react'
import { DragDropContext } from 'react-beautiful-dnd'
import {boards, search, issue} from "../../../services";
import {Typography} from "@material-ui/core";
import {List} from "./Lists/List";
import {Helmet} from "react-helmet";
import { Lists } from './Styles'
import {issueHelper} from "../../../helpers";

class ViewBoardPage extends React.Component<any, any> {

    constructor(props) {
        super(props)

        this.state = {
            board: null,
            issues: []
        }

        this.loadState = this.loadState.bind(this)
        this.onDragEnd = this.onDragEnd.bind(this)
        this.searchForIssues = this.searchForIssues.bind(this)
    }

    loadState() {
        const { match: { params } } = this.props;
        boards.getBoardById(params.boardId)
            .then(board => this.setState({ board }))
            .then(_ => this.searchForIssues())
    }

    searchForIssues() {
        search.searchIssues('projectKey=DEMO')
            .then(issues => this.setState({ issues }))
    }

    componentDidMount() {
        this.loadState()
    }

    isPositionChanged (destination, source) {
        if (!destination) return false;
        const isSameList = destination.droppableId === source.droppableId;
        const isSamePosition = destination.index === source.index;
        return !isSameList || !isSamePosition;
    }

    onDragEnd({ draggableId, destination, source }) {
        // dropped outside the list
        if (!destination) return
        // Dropped into the same position
        if (!this.isPositionChanged(destination, source)) return

        // draggableId is the issue id
        // source.droppableId = "To Do"
        // destination.droppableId = "In Progress"
        // what needs to be done is to get workflowState:

        const matchingDestination = this.state.board.boardColumns.filter(
            column => column.workflowStates[0].label == destination.droppableId.toUpperCase()
        )

        const targetWorkflowState = matchingDestination[0].workflowStates[0];
        const matchedIssue = this.state.issues.filter(issue => issue.id === draggableId)[0]

        issue.getWorkflowTransitions(issueHelper.buildExternalKey(matchedIssue))
            .then(transitions => {
                issue.postWorkflowTransitions(issueHelper.buildExternalKey(matchedIssue), transitions.filter(
                    transition => transition.toNode === targetWorkflowState.label
                )[0])
                    .then(_ => this.searchForIssues());
            })
    }

    render() {
        if (this.state.board === null){
            return(
                <React.Fragment>
                    <Typography component="h2" variant="h1" align="center" gutterBottom>
                        Board not found
                    </Typography>
                </React.Fragment>
            )
        }

        return (
            <React.Fragment>
                <Helmet title={this.state.board.name} />
                <DragDropContext onDragEnd={this.onDragEnd}>
                <Lists>
                    {this.state.board.boardColumns.map(boardColumn =>
                        <List boardColumn={boardColumn} key={boardColumn.label} issues={this.state.issues}/>
                    )}
                </Lists>
                </DragDropContext>
            </React.Fragment>
        )
    }
}

export { ViewBoardPage }