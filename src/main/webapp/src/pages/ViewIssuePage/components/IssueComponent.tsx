import React, { useEffect, useState } from "react"
import {Helmet} from "react-helmet";
import {
    Breadcrumbs as MuiBreadcrumbs, Button,
    Card as MuiCard,
    CardContent,
    Divider as MuiDivider,
    Grid,
    Link,
    Menu,
    MenuItem,
    Typography
} from "@material-ui/core";
import {DropzoneArea} from "material-ui-dropzone";
import styled, { createGlobalStyle }  from "styled-components";
import {spacing} from "@material-ui/system";
import {LinkProps, NavLink as RouterNavLink} from "react-router-dom";
import EditableContainer from "../../../components/EditableContainer";
import Field from "../../../components/StandardTextField";
import StatusChip from "./StatusChip";
import PeopleField from "./PeopleField";
import RedactorField from "../../../components/RedactorField";
import {RoundTimeAgo} from "../../../components/RoundTimeAgo";
import IssueComment from "./CommentsComponent";
import {issueActions} from "../../../actions";
import { issue as issueService} from '../../../services'
import WorkflowApplication from '../../../components/workflow/WorkflowApplication';
import LabelsField from "./LabelsField";
import {issueHelper, isAuthenticated} from "../../../helpers";
import AuditHistory from "./AuditHistoryComponent";

import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import Paper from '@material-ui/core/Paper';

const Breadcrumbs = styled(MuiBreadcrumbs)(spacing);
const NavLink = React.forwardRef<LinkProps, any>((props, ref) => (
    <RouterNavLink innerRef={ref} {...props} />
));
const GlobalStyleDropzone = createGlobalStyle`
  &&  .MuiDropzoneArea-root {
    max-height: 51px;
  }f
`

const Card = styled(MuiCard)(spacing);
const Divider = styled(MuiDivider)(spacing);

function a11yProps(index: any) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box p={3}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}


const IssueComponent = ({issue, project, initialWorkflowTransitions, props}) => {
    const [anchorElTransitionMenu, setAnchorElTransitionMenu] = React.useState<null | HTMLElement>(null);
    const [workflowTransitions, setWorkflowTransitions] = useState(initialWorkflowTransitions);
    const [tabValue, setTabValue] = React.useState(0);


    useEffect(() => {
        setWorkflowTransitions(initialWorkflowTransitions)
    }, [initialWorkflowTransitions])

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorElTransitionMenu(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorElTransitionMenu(null);
    };

    const workflowOptions = (issue) => {
        return {
            workflowId: issue.workflowId
        }
    }

    const onSaveTitle = (props, issue) => {
        return (e) => {
            issue.title = e.children;
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
        }
    }

    const onSaveDescription = (props, issue) => {
        return   (e) => {
            issue.description = e.children;
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
        }
    }

    const onTransitionIssue = (props, issue, transition) => {
        return e => {
            issueService.postWorkflowTransitions(issueHelper.buildExternalKey(issue), transition)
                .then(() => issueService.getWorkflowTransitions(issueHelper.buildExternalKey(issue)).then(
                    (response) => response.json().then(
                        json => setWorkflowTransitions(previousValue => json)
                    )
                ))
                .then(() => handleClose())
                .then(() => props.dispatch(issueActions.getIssue(issueHelper.buildExternalKey(issue))))
        }
    }

    const onAddComment = (props, issue) => {
        return (e, value) => {
            return props.dispatch(issueActions.addComment(issueHelper.buildExternalKey(issue), value))
        }
    }

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
                <Typography>
                    <Link
                        href={"/browse/"+issueHelper.buildExternalKey(issue)}
                    >{issueHelper.buildExternalKey(issue)}: {issue.title}</Link></Typography>
            </Breadcrumbs>
            <Divider my={8} />
            {isAuthenticated() &&
            <Card mb={6}>
                <CardContent>
                    <React.Fragment>
                        <Button
                            aria-controls="transition-menu"
                            aria-haspopup="true"
                            color="primary"
                            onClick={handleClick}
                            disabled={workflowTransitions.length == 0}
                        >
                            Transition
                        </Button>
                        <Menu
                            id="transition-menu"
                            anchorEl={anchorElTransitionMenu}
                            keepMounted
                            getContentAnchorEl={null}
                            anchorOrigin={{vertical: "bottom", horizontal: "center"}}
                            transformOrigin={{vertical: "top", horizontal: "center"}}
                            open={Boolean(anchorElTransitionMenu)}
                            onClose={handleClose}
                            color={"primary"}
                        >
                            {workflowTransitions.map(transition =>
                                <MenuItem
                                    onClick={onTransitionIssue(props, issue, transition)}
                                >{transition.label}</MenuItem>
                            )}
                        </Menu>
                    </React.Fragment>
                </CardContent>
            </Card>
            }
            <Card mb={6}>
                <CardContent>
                    <Typography variant="h6">
                        Details
                    </Typography>
                    <br />
                    <Grid container>
                        <Grid container xs={8}>
                            <Grid item xs={2}>
                                Type
                            </Grid>
                            <Grid item xs={4}>
                                Feature Request
                            </Grid>
                            <Grid item xs={2}>Status</Grid>
                            <Grid item xs={4}>
                                { issue.workflowState &&
                                <StatusChip issue={issue} />
                                }
                            </Grid>
                            <Grid item xs={2}>Priority</Grid>
                            <Grid item xs={4}><Typography>{issue.issuePriority}</Typography></Grid>
                            <Grid item xs={2}>
                                Resolution
                            </Grid>
                            <Grid item xs={4}>
                                <Typography>Unresolved</Typography>
                            </Grid>
                            <Grid item xs={2}>
                                Labels
                            </Grid>
                            <Grid item xs={10}>
                                <LabelsField
                                    currentLabels={issue.labels}
                                    onLabelChange={(values) => {
                                        issue.labels = values
                                        props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
                                    }}
                                />
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
            {isAuthenticated() &&
            <Card mb={6}>
                <CardContent>
                    <Typography variant="h6">
                        Attachments
                    </Typography>
                    <DropzoneArea showFileNamesInPreview={true} showFileNames={true}/>
                </CardContent>
            </Card>
            }
            <Card mb={6}>
                <CardContent>
                    <Typography variant="h6">
                        Activity
                    </Typography>
                    <Paper square>
                        <Tabs
                            value={tabValue}
                            onChange={(e, newValue) => setTabValue(newValue)}
                            aria-label="simple tabs example"
                            indicatorColor="primary"
                            textColor="primary"

                        >
                            <Tab label="Comments" {...a11yProps(0)} />
                            <Tab label="Workflow" {...a11yProps(1)} />
                            <Tab label="History" {...a11yProps(2)} />
                        </Tabs>
                    </Paper>
                    <TabPanel value={tabValue} index={0}>
                        {issue.comments &&
                        <ul>
                            {issue.comments.map((comment, _) =>
                                <IssueComment key={comment.id} comment={comment}/>
                            )}
                        </ul>
                        }
                        {!issue.comments || issue.comments.length == 0 &&
                        <React.Fragment>No comments have been made.</React.Fragment>
                        }
                        <br />
                        <br />
                        {isAuthenticated() &&
                        <RedactorField saveFn={onAddComment(props, issue)}/>
                        }
                    </TabPanel>
                    <TabPanel value={tabValue} index={1}>
                        {workflowOptions(issue).workflowId &&
                        <WorkflowApplication options={workflowOptions(issue)}/>
                        }
                    </TabPanel>
                    <TabPanel value={tabValue} index={2}>
                        <AuditHistory externalId={issueHelper.buildExternalKey(issue)} />
                    </TabPanel>
                </CardContent>
            </Card>
        </React.Fragment>
    )
}

export default IssueComponent