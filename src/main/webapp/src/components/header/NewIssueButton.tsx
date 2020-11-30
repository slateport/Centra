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
import UserPickerField from "../UserPickerField";
import {connect} from "react-redux";
import Init from "../../domain/Init";

const Grid = styled(MuiGrid)(spacing)

interface INewIssueButtonProps {
    projectId?: string,
    dispatch: Function,
    init? : Init
}

class NewIssueButton extends React.Component<INewIssueButtonProps, any> {
    private id: string;

    constructor(props) {
        super(props);

        this.state = {
            open: false,
            projectList: [],
            issueTypeList: [],
            labelsList: [],
            priorityList: [],
            projectId: '',
            title: '',
            description: '',
            issueTypeId: '',
            issuePriorityId: '',
            labels: [],
            assigneeId: null,
        }

        this.id = randomUuidString();

        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleChange = this.handleChange.bind(this);
        this.createIssueAndReturn = this.createIssueAndReturn.bind(this)
        this.onChangeProject = this.onChangeProject.bind(this)
    }

    onChangeProject (e) {
        const { value } = e.target;
        // value is the project key

        projectService.getIssueTypesForProject(value)
            .then(issueTypeList => this.setState({issueTypeList}))

        projectService.getPrioritiesForProject(value)
            .then(priorityList => this.setState({priorityList}))

        this.handleChange(e)
    }

    handleClickOpen() {
        this.setState({open: true});
    }

    handleClose() {
        this.setState({open: false});
    }

    componentDidMount() {
        projectService.getAllProjects().then(projectList => this.setState({ projectList }))

        if (this.props.projectId) {
            this.onChangeProject({target: {value: this.props.projectId }})
            this.setState({projectId: this.props.projectId})
        }
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    createIssueAndReturn() {
        issueService.createIssue(
            this.state.title,
            this.state.description,
            this.state.projectId,
            this.state.labels,
            this.state.assigneeId,
            this.state.issueTypeId,
            this.state.issuePriorityId
        )
            .then(response => {
                const externalId = issueHelper.buildExternalKey(response.data);
                history.push("/browse/" + externalId)
                location.reload()
            })
            .catch(e => this.props.dispatch(alertActions.error("Failed to create issue")))
    }

    render() {
        return (
            <React.Fragment>
                <Button variant="contained" color="primary" size={"small"} onClick={this.handleClickOpen}>
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
                                        onChange={this.onChangeProject}
                                        variant="outlined"
                                        fullWidth
                                        defaultValue={(typeof this.props.projectId == 'string') ? this.props.projectId : ''}
                                    >
                                        {this.state.projectList.map((project) =>
                                            <MenuItem value={project.projectKey} key={project.id}>{project.projectName}</MenuItem>
                                        )}
                                    </Select>
                                </Grid>
                                <Grid item xs={3} p={2}>Issue Type</Grid>
                                <Grid item xs={9} p={2}>
                                    <Select
                                        id="issueTypeId"
                                        name ="issueTypeId"
                                        onChange={this.handleChange}
                                        variant="outlined"
                                        fullWidth
                                    >
                                        {this.state.issueTypeList.map((issueType) =>
                                            <MenuItem value={issueType.id} key={issueType.id}>{issueType.label}</MenuItem>
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
                                <Grid item xs={3} p={2}>Priority</Grid>
                                <Grid item xs={9} p={2}>
                                    <Select
                                        id="issuePriorityId"
                                        name ="issuePriorityId"
                                        onChange={this.handleChange}
                                        variant="outlined"
                                        fullWidth
                                    >
                                        {this.state.priorityList.map((priority) =>
                                            <MenuItem value={priority.id} key={priority.id}>{priority.label}</MenuItem>
                                        )}
                                    </Select>
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
                                <Grid item xs={3} p={2}>Assignee</Grid>
                                <Grid item xs={9} p={2}>
                                    <UserPickerField userId={this.props.init?.user?.id} handleFn={(assigneeId) => this.setState({assigneeId})}/>
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
    }
}

function mapStateToProps (state) {
    const { alert, init } = state
    return {
        alert, init
    }
}

const connectedNewIssueButton = connect(mapStateToProps)(NewIssueButton)
export { connectedNewIssueButton as NewIssueButton }
