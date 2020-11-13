import React from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
} from '@material-ui/core';

import NewIssueView from "../../pages/NewIssueView/NewIssueView";

export default class NewIssueButton extends React.Component<any, any>{
    constructor(props) {
        super(props);

        this.state = {
            open: false
        }

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
    }

    handleClickOpen() {
        this.setState({open: true});
    }

    handleClose() {
        this.setState({open: false});
    }

    render() {

        return (
            <React.Fragment>
                <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                    New Issue
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Create a new issue</DialogTitle>
                    <DialogContent>
                        <NewIssueView />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.handleClose} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        )
    }}
