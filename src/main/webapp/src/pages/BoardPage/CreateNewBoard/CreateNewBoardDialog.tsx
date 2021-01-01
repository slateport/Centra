import React from 'react'
import {Button, Dialog, DialogActions, DialogContent, DialogTitle, Grid as MuiGrid, TextField} from "@material-ui/core";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import ProjectsAutocomplete from '../../../components/ProjectsAutocomplete';

const Grid = styled(MuiGrid)(spacing)

interface ICreateNewBoardDialogProps {
    type: 'Kanban' | 'Scrum'
}

class CreateNewBoardDialog extends React.Component<ICreateNewBoardDialogProps, any> {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
            name: null,
            projectIds: [],
            type: this.props.type.toLowerCase()
        }

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.setProjects = this.setProjects.bind(this)
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

    setProjects(projects: any[]) {
        const projectIds = [];
        projects.map(project => {
            projectIds.push(project.id)
        })

        this.setState({ projectIds })
    }

    render() {
        return (
            <React.Fragment>
                <Button color={"primary"} size={"small"} onClick={this.handleClickOpen}>
                    Create a new {this.props.type} board
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Create a new {this.props.type} board</DialogTitle>
                <DialogContent>
                    <Grid container>
                        <Grid item xs={3} p={2}>Name</Grid>
                        <Grid item xs={9} p={2}>
                            <TextField
                                id={"name"}
                                name={"name"}
                                fullWidth
                                required
                                onChange={this.handleChange}
                                variant="outlined"
                                size={"small"}
                            />
                        </Grid>
                        <Grid item xs={3} p={2}>Project(s)</Grid>
                        <Grid><ProjectsAutocomplete onChangeFunction={(value) => this.setProjects(value)}/></Grid>
                    </Grid>
                </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={() => console.log(this.state)} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        )
    }
}

export { CreateNewBoardDialog }