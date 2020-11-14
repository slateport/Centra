import React from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle, Grid as MuiGrid, MenuItem, Select, TextField,
} from '@material-ui/core';

import {issue as issueService, project as projectService} from "../../services";
import {issueHelper, history} from "../../helpers";
import {alertActions} from "../../actions";
import RedactorTextArea from "../RedactorTextArea";
import {randomUuidString} from "../../helpers/uuid";
import LabelsField from "../../pages/ViewIssuePage/components/LabelsField";
import styled from "styled-components";
import {spacing} from "@material-ui/system";

const Grid = styled(MuiGrid)(spacing)

export default class NewIssueButton extends React.Component<any, any>{
    private id: string;

    constructor(props) {
        super(props);

        this.state = {
            open: false,
            projectList: [],
            labelsList: [],
            projectId: '',
            title: '',
            description: '',
            labels: [],
        }

        this.id = randomUuidString();

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleChange = this.handleChange.bind(this);
        this.createIssueAndReturn = this.createIssueAndReturn.bind(this)
    }

    handleClickOpen() {
        this.setState({open: true});
    }

    handleClose() {
        this.setState({open: false});
    }

    componentDidMount() {
        projectService.getAllProjects().then(
            r => r.json().then(data => this.setState({projectList: data}))
        )
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    createIssueAndReturn() {
        issueService.createIssue(this.state.title, this.state.description, this.state.projectId, this.state.labels)
            .then(response => {

                if (!response.ok) {
                    this.props.dispatch(alertActions.error("Failed to create issue"))
                    return;
                }

                response.json()
                    .then(data => {
                        const externalId = issueHelper.buildExternalKey(data);
                        history.push("/browse/" + externalId)
                        location.reload()
                    })
            })
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
                        <React.Fragment>
                            <Grid container>
                                <Grid item xs={3} p={2}>Project</Grid>
                                <Grid item xs={9} p={2}>
                                    <Select
                                        id="project"
                                        name ="projectId"
                                        onChange={this.handleChange}
                                        variant="outlined"
                                        fullWidth
                                    >
                                        {this.state.projectList.map((project) =>
                                            <MenuItem value={project.projectKey} key={project.id}>{project.projectName}</MenuItem>
                                        )}
                                    </Select>
                                </Grid>
                                <Grid item xs={3} p={2}>Title</Grid>
                                <Grid item xs={9} p={2}>
                                    <TextField
                                        id={"title"}
                                        name={"title"}
                                        fullWidth
                                        required
                                        onChange={this.handleChange}
                                        variant="outlined"
                                    />
                                </Grid>
                                <Grid item xs={3} p={2}>Description</Grid>
                                <Grid item xs={9} p={2}>
                                    <RedactorTextArea id={this.id} handleChange={this.handleChange} name={'description'} />
                                </Grid>
                                <Grid item xs={3} p={2}>Labels</Grid>
                                <Grid item xs={9} p={2}>
                                    <LabelsField
                                        currentLabels={this.state.labels}
                                        onLabelChange={(values) => this.setState({labels: values})}
                                        variant="outlined"
                                        fullWidth
                                        st
                                    />
                                </Grid>
                            </Grid>
                        </React.Fragment>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.createIssueAndReturn} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        )
    }}
