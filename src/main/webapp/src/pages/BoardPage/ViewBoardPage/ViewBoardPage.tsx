import React from 'react'
import { DragDropContext } from 'react-beautiful-dnd'
import {boards} from "../../../services";
import {Typography} from "@material-ui/core";
import {List} from "./Lists/List";
import {Helmet} from "react-helmet";
import { Lists } from './Styles'

class ViewBoardPage extends React.Component<any, any> {

    constructor(props) {
        super(props)

        this.state = {
            board: null
        }

        this.loadState = this.loadState.bind(this)
        this.onDragEnd = this.onDragEnd.bind(this)
    }

    loadState() {
        const { match: { params } } = this.props;
        boards.getBoardById(params.boardId)
            .then(board => this.setState({ board }))
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
                        <List boardColumn={boardColumn} key={boardColumn.label}/>
                    )}
                </Lists>
                </DragDropContext>
            </React.Fragment>
        )
    }
}

export { ViewBoardPage }