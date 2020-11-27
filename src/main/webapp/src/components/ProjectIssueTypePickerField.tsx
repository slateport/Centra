import React from "react";
import {project} from "../services";
import TextField from "@material-ui/core/TextField";
import Autocomplete from "@material-ui/lab/Autocomplete";
import IssueType from "../domain/IssueType";

interface IProjectIssueTypePickerFieldProps {
    projectKey: string,
    selectedId?: string,
    handleFn: Function
}

interface IProjectIssueTypePickerFieldState {
    issueTypes: IssueType[]
}


class ProjectIssueTypePickerField extends React.Component<IProjectIssueTypePickerFieldProps, IProjectIssueTypePickerFieldState> {

    constructor(props) {
        super(props);

        this.state = {
            issueTypes: [],
        }
    }

    componentDidMount() {
        project.getIssueTypesForProject(this.props.projectKey)
            .then(issueTypes => this.setState({ issueTypes }))
    }

    render() {
        return (
            <React.Fragment>
                <Autocomplete
                    id="issuetype-picker-field"
                    options={this.state.issueTypes}
                    getOptionLabel={(option: IssueType) => option.label}
                    value={this.state.issueTypes.find(u => (this.props.selectedId) ? u.id == this.props.selectedId : {}) || {}}
                    getOptionSelected={(option: IssueType, value: IssueType) => option.id == value.id}
                    renderInput={(params) => <TextField {...params} variant="outlined" />}
                    onChange={(_, value: IssueType) => this.props.handleFn(value.id)}
                />
            </React.Fragment>
        );
    }
}

export default ProjectIssueTypePickerField