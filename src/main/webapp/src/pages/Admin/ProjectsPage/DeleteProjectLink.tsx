import React from "react"
import {project as projectService } from '../../../services'
import {alertActions} from "../../../actions";
import {Button, Dialog, DialogActions, DialogContent, DialogTitle   , Typography} from "@material-ui/core";
import DeleteIcon from '@material-ui/icons/Delete';


interface IDeleteProjectLinkProps {
    project: { id, projectName }
}

class DeleteProjectLink extends React.Component<IDeleteProjectLinkProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            open: false
        }

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.deleteProjectAndIssues = this.deleteProjectAndIssues.bind(this)
    }


    handleClickOpen() {
        this.setState({open: true});
    }

    handleClose() {
        this.setState({open: false});
    }


    deleteProjectAndIssues() {
        projectService.deleteProject(this.props.project.id)
            .then(r => location.reload())
    }

    render() {
        return (
            <React.Fragment>
                <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                    <DeleteIcon />  
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Create a new project</DialogTitle>
                    <DialogContent>
                        <React.Fragment>
                            <Typography>Please confirm you'd like to delete project {this.props.project.projectName}</Typography>
                        </React.Fragment>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.deleteProjectAndIssues} color="primary">
                            Delete
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        )
    }
}

export { DeleteProjectLink }