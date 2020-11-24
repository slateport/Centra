import React from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle, Grid as MuiGrid, TextField,
} from '@material-ui/core';

import styled from "styled-components";
import {spacing} from "@material-ui/system";
import { project as projectService } from '../../../services'

const Grid = styled(MuiGrid)(spacing)

class NewProjectButton extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            open: false,
            projectKey : '',
            projectName: '',
            description: '',
        }

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.createProjectAndReturn = this.createProjectAndReturn.bind(this)
    }

    handleClickOpen() {
        this.setState({open: true});
    }

    handleClose() {
        this.setState({open: false});
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    createProjectAndReturn(e) {
        projectService.createNewProject(this.state.projectKey, this.state.projectName, this.state.description)
            .then(r => {
                if (r.ok) {
                    location.reload();
                } else {
                    r.json().then(data => alert(data.message))
                }
            })
    }

    render() {
        return (
            <React.Fragment>
                <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                    New Project
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Create a new project</DialogTitle>
                    <DialogContent>
                        <React.Fragment>
                            <Grid container>
                                <Grid item xs={3} p={2}>Name</Grid>
                                <Grid item xs={9} p={2}>
                                    <TextField
                                        id={"projectName"}
                                        name={"projectName"}
                                        fullWidth
                                        required
                                        onChange={this.handleChange}
                                        variant="outlined"
                                    />
                                </Grid>
                                <Grid item xs={3} p={2}>Key</Grid>
                                <Grid item xs={9} p={2}>
                                    <TextField
                                        id={"projectKey"}
                                        name={"projectKey"}
                                        fullWidth
                                        required
                                        onChange={this.handleChange}
                                        variant="outlined"
                                    />
                                </Grid>
                                <Grid item xs={3} p={2}>Description</Grid>
                                <Grid item xs={9} p={2}>
                                    <TextField
                                        id={"description"}
                                        name={"description"}
                                        fullWidth
                                        required
                                        onChange={this.handleChange}
                                        variant="outlined"
                                    />
                                </Grid>
                            </Grid>
                        </React.Fragment>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.createProjectAndReturn} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        )
    }
}

export { NewProjectButton }