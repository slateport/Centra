import React from "react";
import { connect } from "react-redux";
import { issueActions } from "../../actions";
import { Typography } from "@material-ui/core";
import { createGlobalStyle } from "styled-components";
import {projectActions} from "../../actions/project";
import IssueComponent from "./components/IssueComponent";

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

        this.loadState();
    }

    loadState(){
        const { match: { params } } = this.props;
        this.props.dispatch(issueActions.getIssue(params.externalId))
            .then(() => {
                this.props.dispatch(projectActions.getProject(this.props.issue.projectId))
                this.props.dispatch(issueActions.getIssueComments(params.externalId))
            })
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
                <IssueComponent issue={issue} project={project} props={this.props} />
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