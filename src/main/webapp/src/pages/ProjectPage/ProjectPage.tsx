import React from 'react'
import { Helmet } from 'react-helmet'
import {Card, CardContent, Divider as MuiDivider, Grid, Typography} from "@material-ui/core";
import {project} from "../../services";
import styled from "styled-components";
import {spacing} from "@material-ui/system";

const Divider = styled(MuiDivider)(spacing);

class ProjectPage extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            project: undefined
        }
    }


    componentDidMount() {
        const { match: { params } } = this.props;
        project.getProject(params.projectId)
            .then(projectResult => this.setState({ project: projectResult }))
            .catch(_ => this.setState({ project: null }))

    }

    render(){
        if (this.state.project === undefined) {
            return (
                <React.Fragment>
                    <Helmet title={"Project"}/>
                    <Typography component="h2" variant="h1" align="center" gutterBottom>
                        Loading project...
                    </Typography>
                </React.Fragment>
            )
        }

        if (this.state.project === null) {
            return (
                <React.Fragment>
                    <Helmet title={"Project"}/>
                    <Typography component="h2" variant="h1" align="center" gutterBottom>
                        Project not found
                    </Typography>
                </React.Fragment>
            )
        }

        return (
            <React.Fragment>
                <Helmet title={"Project " + this.state.project.projectName}/>
                <Typography variant="h3" display="block">{this.state.project.projectName}</Typography>
                <br />
                <Divider my={6}/>
                <br />
                <Grid container spacing={6}>
                    <Grid item xs={6}>
                        <Card>
                            <CardContent>
                                <Typography variant="h5" display="block">Description</Typography>
                                <p>{this.state.project.description}</p>
                            </CardContent>
                        </Card>
                    </Grid>
                </Grid>
            </React.Fragment>
        )
    }
}

export { ProjectPage }
