import React from "react";
import { Component } from "react";
import {Grid, TextField, Select, MenuItem} from "@material-ui/core";

import RedactorX from '../../static/redactorx/redactorx'
import '../../static/redactorx/redactorx.css'

import { project as projectService } from '../../services'

const  randomUuidString = () => {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

class NewIssueView extends Component<any, any> {
    private id: string;
    private redactor: any;

    constructor(props) {
        super(props);

        this.id = randomUuidString();

        this.state = {
            projectList: [],
            projectId: '',
            title: '',
            description: '',
            labels: '',
        };

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        projectService.getAlProjects().then(
            r => r.json().then(data => this.setState({projectList: data}))
        )

        this.redactor = RedactorX('#'+this.id)
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    render() {
        return (
            <React.Fragment>
               <Grid container>
                   <Grid item xs={3}>Project</Grid>
                   <Grid item xs={9}>
                       <Select
                           id="project"
                           name ="project"
                           onChange={this.handleChange}
                       >
                           {this.state.projectList.map((project) =>
                               <MenuItem value={project.id}>{project.projectName}</MenuItem>
                           )}
                       </Select>
                   </Grid>
                   <Grid item xs={3}>Title</Grid>
                   <Grid item xs={9}>
                       <TextField
                        id={"title"}
                        name={"title"}
                        fullWidth
                        required
                       />
                   </Grid>
                   <Grid item xs={3}>Description</Grid>
                   <Grid item xs={9}>
                       <textarea
                           id={this.id}
                           name={"title"}
                       />
                   </Grid>
               </Grid>
            </React.Fragment>
        )
    }
}

export default NewIssueView