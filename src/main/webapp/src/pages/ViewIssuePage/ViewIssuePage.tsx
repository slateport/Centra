import React from "react";
import { connect } from "react-redux";
import { issueActions } from "../../actions";
import {
    Typography,
    Breadcrumbs as MuiBreadcrumbs,
    Link,
    Divider as MuiDivider,
    Grid,
    Card as MuiCard,
    CardContent
} from "@material-ui/core";
import styled, { createGlobalStyle } from "styled-components";
import { spacing } from "@material-ui/system";
import { NavLink as RouterNavLink, LinkProps} from "react-router-dom";
import {Helmet} from "react-helmet";
import {projectActions} from "../../actions/project";
import IssueComment from "./components/IssuesComponent";
import PeopleField from "./components/PeopleField";
import EditableContainer from "../../components/EditableContainer";
import Field from '../../components/StandardTextField'
import RedactorField from "../../components/RedactorField"
import { DropzoneArea } from 'material-ui-dropzone'

const Breadcrumbs = styled(MuiBreadcrumbs)(spacing);

const NavLink = React.forwardRef<LinkProps, any>((props, ref) => (
    <RouterNavLink innerRef={ref} {...props} />
));
const GlobalStyleDropzone = createGlobalStyle`
  [class^="DropzoneArea-dropZone"] {
    max-height: 100px;
  }
  
  && .MuiDropzoneArea-root {
    min-height: 100px;
`


const Card = styled(MuiCard)(spacing);
const Divider = styled(MuiDivider)(spacing);

class ViewIssuePage extends React.Component<any, any> {
    constructor(props) {
        super(props);

        this.loadState();
    }

    onSaveTitle = (e) => {
        const { match: { params } } = this.props;
        const title = e.children;
        const issue = this.props.issue;
        issue.title = title;

        this.props.dispatch(issueActions.updateIssue(params.externalId, issue))
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
        if (!this.props.issue){
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
                <Helmet title={issue.title} />
                <Typography variant="h3" gutterBottom display="inline">
                    <EditableContainer handlefn={this.onSaveTitle} Component={Field}>
                        {issue.title}
                    </EditableContainer>
                </Typography>
                <Breadcrumbs aria-label="Breadcrumb" mt={2}>
                    <Link component={NavLink} exact to="/">
                        {project.projectName}
                    </Link>
                    <Typography>{issue.title}</Typography>
                </Breadcrumbs>
                <Divider my={8} />
                <Card mb={6}>
                    <CardContent>
                        <Typography variant="h6">
                           Details
                        </Typography>
                        <br />
                <Grid container>
                    <Grid container xs={8}>
                        <Grid item xs={3}>
                            Type
                        </Grid>
                        <Grid item xs={3}>
                            Feature Request
                        </Grid>
                        <Grid item xs={3}>
                            Priority
                        </Grid>
                        <Grid item xs={3}>
                            <Typography>{issue.issuePriority}</Typography>
                        </Grid>
                    </Grid>
                    <Grid container xs={1} />
                    <Grid container xs={3}>
                        <Grid container xs={12}>
                            <Grid item xs={6}>
                               Assignee:
                            </Grid>
                            <Grid item xs={6}>
                                <PeopleField userId={issue.assignee} />
                            </Grid>
                            <Grid item xs={6}>
                                Reporter:
                            </Grid>
                            <Grid item xs={6}>
                                <PeopleField userId={issue.createdByUserId} />
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
                </CardContent>
                </Card>
                <Card mb={6}>
                    <CardContent>
                        <Typography variant="h6">
                            Description
                        </Typography>
                        <EditableContainer Component={RedactorField}>
                            {issue.description}
                        </EditableContainer>
                    </CardContent>
                </Card>
                <Card mb={6}>
                    <CardContent>
                        <Typography variant="h6">
                            Attachments
                        </Typography>
                        <DropzoneArea showFileNamesInPreview={true} showFileNames={true} />
                    </CardContent>
                </Card>
                <Card mb={6}>
                    <CardContent>
                        <Typography variant="h6">
                            Comments
                        </Typography>
                        {this.props.issue.comments &&
                        <ul>
                            {this.props.issue.comments.map((comment, _) =>
                                <IssueComment comment={comment}/>
                            )}
                        </ul>
                        }
                        {!this.props.issue.comments || this.props.issue.comments.length == 0 &&
                        <React.Fragment>No comments have been made.</React.Fragment>
                        }
                    </CardContent>
                </Card>
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