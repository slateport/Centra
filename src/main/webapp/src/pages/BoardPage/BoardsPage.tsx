import React from 'react'
import {Helmet} from "react-helmet";
import {Button, Card, CardContent, Divider as MuiDivider, Grid, Typography} from "@material-ui/core";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import {CreateNewBoardDialog} from "./CreateNewBoard";
import {boards} from "../../services";
import {Link} from 'react-router-dom'

const Divider = styled(MuiDivider)(spacing);

class BoardsPage extends React.Component<any, any> {
    constructor(props) {
        super(props);

        this.state = {
            boards: []
        }
    }

    componentDidMount() {
        boards.getAll()
            .then(boards => this.setState({ boards }))
    }

    render() {
        return (
            <React.Fragment>
                <Helmet title="Boards" />
                <Typography variant="h3" display="block">Boards</Typography>
                <br />
                <Divider my={6}/>
                <br />
                <Grid container spacing={6}>
                    <Grid item xs={6}>
                        <Card>
                            <CardContent>
                                <Typography variant="h4" display="block">Create a new board</Typography>
                                <br />
                                <Typography variant="h6" display="block">Scrum</Typography>
                                <p>Scrum focuses on planning, committing and delivering time-boxed chunks of work called Sprints.</p>
                                <CreateNewBoardDialog type={'Scrum'} />
                                <Divider my={6}/>
                                <Typography variant="h6" display="block">Kanban</Typography>
                                <p>
                                    Kanban focuses on visualising your workflow and limiting work-in-progress to facilitate incremental improvements to your existing process.
                                </p>
                                <CreateNewBoardDialog type={'Kanban'} />
                            </CardContent>
                        </Card>
                    </Grid>
                    <Grid item xs={6}>
                        <Card>
                            <CardContent>
                                <Typography variant="h4" display="block">List of Boards</Typography>
                                {this.state.boards.length === 0 &&
                                <Typography display="block">No boards have been created yet.</Typography>
                                }

                                {this.state.boards.map(board =>
                                   <React.Fragment>
                                       <Link component={Button} to={`/boards/${board.id}`}>{board.name}</Link>
                                       <br />
                                   </React.Fragment>
                                )}
                            </CardContent>
                        </Card>
                    </Grid>
                </Grid>
            </React.Fragment>
        )
    }
}

export { BoardsPage }