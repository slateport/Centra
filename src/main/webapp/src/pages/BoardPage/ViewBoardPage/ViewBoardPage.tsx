import React from 'react'
import {boards} from "../../../services";
import {Typography} from "@material-ui/core";

class ViewBoardPage extends React.Component<any, any> {

    constructor(props) {
        super(props)

        this.state = {
            board: null
        }

        this.loadState = this.loadState.bind(this)
    }

    loadState() {
        const { match: { params } } = this.props;
        boards.getBoardById(params.boardId)
            .then(board => this.setState({ board }))
    }

    componentDidMount() {
        this.loadState()
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
            <code>{JSON.stringify(this.state.board)}</code>
        )
    }
}

export { ViewBoardPage }