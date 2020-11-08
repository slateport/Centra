import React from "react"
import {Helmet} from "react-helmet";
import {
    Breadcrumbs as MuiBreadcrumbs,
    Card as MuiCard,
    CardContent,
    Divider as MuiDivider,
    Grid,
    Link,
    Typography
} from "@material-ui/core";
import EditableContainer from "../../../components/EditableContainer";
import Field from "../../../components/StandardTextField";
import StatusChip from "./StatusChip";
import PeopleField from "./PeopleField";
import RedactorField from "../../../components/RedactorField";
import {RoundTimeAgo} from "../../../components/RoundTimeAgo";
import {DropzoneArea} from "material-ui-dropzone";
import IssueComment from "./CommentsComponent";
import styled, { createGlobalStyle }  from "styled-components";
import {spacing} from "@material-ui/system";
import {LinkProps, NavLink as RouterNavLink} from "react-router-dom";
import {issueActions} from "../../../actions";

const Breadcrumbs = styled(MuiBreadcrumbs)(spacing);
const NavLink = React.forwardRef<LinkProps, any>((props, ref) => (
    <RouterNavLink innerRef={ref} {...props} />
));
const GlobalStyleDropzone = createGlobalStyle`
  &&  .MuiDropzoneArea-root {
    min-height: 51px;
  }
`

const Card = styled(MuiCard)(spacing);
const Divider = styled(MuiDivider)(spacing);

const onSaveTitle = (props, issue) => {
    return (e) => {
        issue.title = e.children;
        props.dispatch(issueActions.updateIssue(buildExternalKey(issue), issue))
    }
}

const onSaveDescription = (props, issue) => {
    return (e) => {
        issue.description = e.children;
        props.dispatch(issueActions.updateIssue, buildExternalKey(issue))
    }
}

const buildExternalKey = (issue) => issue.projectKey +'-'+ issue.externalId

const IssueComponent = ({issue, project, props}) => {
    return (
        <React.Fragment>
            <GlobalStyleDropzone />
            <Helmet title={issue.title} />
            <Typography variant="h3" gutterBottom display="inline">
                <EditableContainer handlefn={onSaveTitle(props, issue)} Component={Field}>
                    {issue.title}
                </EditableContainer>
            </Typography>
            <Breadcrumbs aria-label="Breadcrumb" mt={2}>
                <Link component={NavLink} exact to="/">
                    {project.projectName}
                </Link>
                <Typography>{buildExternalKey(issue)}: {issue.title}</Typography>
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
                            <Grid item xs={3}>Status</Grid>
                            <Grid item xs={3}>
                                { issue.workflowState &&
                                <StatusChip issue={issue} />
                                }
                            </Grid>
                            <Grid item xs={3}>Priority</Grid>
                            <Grid item xs={3}><Typography>{issue.issuePriority}</Typography></Grid>
                            <Grid item xs={3}>
                                Resolution
                            </Grid>
                            <Grid item xs={3}>
                                <Typography>Unresolved</Typography>
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
                        <Divider my={8} />
                        <Grid container xs={8}>
                            <Typography variant="h6">
                                Description
                            </Typography>
                            <Grid item xs={12}>
                                <EditableContainer Component={RedactorField} handlefn={onSaveDescription(props, issue)}>
                                    {issue.description}
                                </EditableContainer>
                            </Grid>
                        </Grid>
                        <Grid container xs={1} />
                        <Grid container xs={3}>
                            {issue.createdDate &&
                            <React.Fragment>
                                <Grid item xs={6}>
                                    Date created:
                                </Grid>
                                <Grid item xs={6}>
                                    <RoundTimeAgo date={new Date(issue.createdDate)} />
                                </Grid>
                            </React.Fragment>
                            }

                            {issue.lastModifiedDate &&
                            <React.Fragment>
                                <Grid item xs={6}>
                                    Last modified:
                                </Grid>
                                <Grid item xs={6}>
                                    <RoundTimeAgo date={new Date(issue.lastModifiedDate)} />
                                </Grid>
                            </React.Fragment>
                            }
                        </Grid>
                    </Grid>
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
                    {issue.comments &&
                    <ul>
                        {issue.comments.map((comment, _) =>
                            <IssueComment comment={comment}/>
                        )}
                    </ul>
                    }
                    {!issue.comments || issue.comments.length == 0 &&
                    <React.Fragment>No comments have been made.</React.Fragment>
                    }
                </CardContent>
            </Card>
        </React.Fragment>
    )
}

export default IssueComponent