import React from "react";
import AddIcon from "@material-ui/icons/Add";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Grid,
    TextField
} from "@material-ui/core";
import {issue as issueService} from "../../../services";
import {alertActions} from "../../../actions";

class NewIssueLinkDialog extends React.Component<{ issueId },any> {

    constructor(props) {
        super(props);

        this.state = {
            open: false,
            nodePublicId: this.props.issueId,
            linkPublicId: ''
        }

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.linkIssueAndReturn = this.linkIssueAndReturn.bind(this)
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

    linkIssueAndReturn (e) {
        issueService.createLink(this.state.nodePublicId, this.state.linkPublicId)
            .then(response => {
                if (response.data.id) {
                    location.reload()
                } else {
                    alertActions.error("Failed to link issues: " + response.data.message)
                }
            })
    }

    render() {
        return (
            <React.Fragment>
                <Button color={"primary"} size={"small"} onClick={this.handleClickOpen}>
                    <AddIcon />
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Link an issue to {this.props.issueId}</DialogTitle>
                    <DialogContent>
                        <React.Fragment>
                            <Grid container>
                                <Grid item xs={3} p={2}>Issue ID</Grid>
                                <Grid item xs={9} p={2}>
                                    <TextField
                                        id={"linkPublicId"}
                                        name={"linkPublicId"}
                                        fullWidth
                                        required
                                        onChange={this.handleChange}
                                        variant="outlined"
                                        size={"small"}
                                    />
                                </Grid>
                            </Grid>
                        </React.Fragment>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.linkIssueAndReturn} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>

            </React.Fragment>
        )
    }

}

export default NewIssueLinkDialog