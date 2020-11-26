import React from "react";
import {connect} from "react-redux";
import {alertActions, issueActions} from "../../actions";
import {Grid, Typography} from "@material-ui/core";
import {createGlobalStyle} from "styled-components";
import {projectActions} from "../../actions/project";
import IssueComponent from "./components/IssueComponent";
import {issue} from "../../services";

const GlobalStyleDropzone = createGlobalStyle`
  [class^="DropzoneArea-dropZone"] {
    max-height: 100px;
  }
  
  && .MuiDropzoneArea-root {
    min-height: 100px;
`



class ViewIssuePage extends React.Component<any, any> {
    constructor(props) {
        super(props);

        this.state = {
            transitions: []
        }
    }

    loadState(){
        const { match: { params } } = this.props;
        this.props.dispatch(issueActions.getIssue(params.externalId))
            .then(() => {
                if (this.props.issue.projectId) {
                    this.props.dispatch(projectActions.getProject(this.props.issue.projectId))
                }
                this.props.dispatch(issueActions.getIssueComments(params.externalId))
                issue.getWorkflowTransitions(params.externalId)
                    .then((resp) => {
                            resp.json()
                                .then(data => this.setState({transitions:data}));
                            return Promise.resolve();
                        },
                        (error) => {
                            this.props.dispatcj(alertActions.error("ffailed to fetch transitions"))
                        });
            })
    }

    componentDidMount() {
        this.loadState();
    }

    render() {
        if (Object.keys(this.props.issue).length === 0 || !this.props.issue){
            return (
                <React.Fragment>
                    <Typography component="h2" variant="h1" align="center" gutterBottom>
                        Issue not found
                    </Typography>
                </React.Fragment>
            )
        }

        const { issue, project } = this.props;

        return (
            <React.Fragment>
                <GlobalStyleDropzone />
                <Grid container>
                    <Grid item xs={1} />
                    <Grid item xs={10}>
                        <IssueComponent
                            issue={issue}
                            project={project}
                            initialWorkflowTransitions={this.state.transitions}
                            props={this.props}
                        />
                    </Grid>
                    <Grid item xs={1} />
                </Grid>
            </React.Fragment>
        )
    }
}


function mapStateToProps(state) {
    const { init, issue, project } = state;
    return {
        init, issue, project
    };
}

const connectedViewIssuePage = connect(mapStateToProps)(ViewIssuePage);
export { connectedViewIssuePage as ViewIssuePage };