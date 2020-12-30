import React, {useEffect, useRef, useState} from "react"
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
import {DropzoneArea} from "material-ui-dropzone";
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import styled, {createGlobalStyle} from "styled-components";
import {spacing} from "@material-ui/system";
import {LinkProps, NavLink as RouterNavLink} from "react-router-dom";
import EditableContainer from "../../../components/EditableContainer";
import {TextArea} from "../../../components/StandardTextArea"
import EditablePeopleField from "./EditablePeopleField";
import RedactorField from "../../../components/RedactorField";
import {RoundTimeAgo} from "../../../components/RoundTimeAgo";
import IssueComment from "./CommentsComponent";
import {issueActions} from "../../../actions";
import {issue as issueService} from '../../../services'
import WorkflowApplication from '../../../components/workflow/WorkflowApplication';
import LabelsField from "./LabelsField";
import {isAuthenticated, issueHelper} from "../../../helpers";
import AuditHistory from "./AuditHistoryComponent";

import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import Paper from '@material-ui/core/Paper';
import RelatedIssuesComponent from "./RelatedIssuesComponent";
import { NewIssueLinkDialog } from "./NewIssueLinkDialog";
import {StatusPicker} from "./StatusPicker";
import {IssueTypePicker} from "./IssueTypePicker";
import {IssuePriorityPicker} from "./IssuePriorityPicker";
import CustomFieldsComponent from "./CustomFieldsComponent";

const Breadcrumbs = styled(MuiBreadcrumbs)(spacing);
const SectionTitle = styled.span`
    text-transform: uppercase;
    font-size:12.5px;
    font-weight: 700;
    color: #5E6C84;
`

const SectionTime = styled.span`
    color: #5E6C84;
    font-size:12.5px;
`

const NavLink = React.forwardRef<LinkProps, any>((props, ref) => (
    <RouterNavLink innerRef={ref} {...props} />
));

const GlobalStyleDropzone = createGlobalStyle`
  &&  .MuiDropzoneArea-root {
    max-height: 51px;
  }
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
    const [workflowTransitions, setWorkflowTransitions] = useState(initialWorkflowTransitions);
    const [tabValue, setTabValue] = React.useState(0);

    useEffect(() => {
        setWorkflowTransitions(initialWorkflowTransitions)
    }, [initialWorkflowTransitions])

    const workflowOptions = (issue) => {
        return {
            workflowId: issue.workflowId
        }
    }

    const onSaveTitle = (props, issue, titleRef) => {
        return _ => {
            if (issue.title === titleRef.current.value) return
            issue.title = titleRef.current.value;
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
        }
    }

    const onSaveDescription = (props, issue) => {
        return (e) => {
            issue.description = e.children;
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
        }
    }

    const onTransitionIssue = (props, issue, transition) => {
        return _ => {
            issueService.postWorkflowTransitions(issueHelper.buildExternalKey(issue), transition)
                .then(() => issueService.getWorkflowTransitions(issueHelper.buildExternalKey(issue))
                    .then(response => setWorkflowTransitions(response))
                )
                .then(() => props.dispatch(issueActions.getIssue(issueHelper.buildExternalKey(issue))))
        }
    }

    const onAddComment = (props, issue) => {
        return (e, value) => {
            return props.dispatch(issueActions.addComment(issueHelper.buildExternalKey(issue), value))
        }
    }

    const onSaveAssignee = (props, issue) => {
        return value => {
            issue.assigneeId = value
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
            location.reload()
        }
    }

    const onSaveReporter = (props, issue) => {
        return value => {
            issue.createdByUserId = value
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
            location.reload()

        }
    }

    const onSaveIssueType = (props, issue) => {
        return value => {
            issue.issueTypeId = value
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
            location.reload()
        }
    }

    const onSavePriority = (props, issue) => {
        return value => {
            issue.issuePriorityId = value
            props.dispatch(issueActions.updateIssue(issueHelper.buildExternalKey(issue), issue))
            location.reload()
        }
    }

    const titleRef = useRef()

    return (
        <React.Fragment>
            <GlobalStyleDropzone />
            <Helmet title={issue.title} />
            <Typography variant="h3" gutterBottom display="inline">
                <TextArea ref={titleRef} onBlur={onSaveTitle(props, issue, titleRef)} id={'title'} defaultValue={issue.title} onKeyDown={event => {
                    if (event.key === 'Enter') {
                        event.currentTarget.blur();
                    }
                }}>
                </TextArea>
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
            <Card mb={6}>
                <CardContent>
                    <Grid container>
                        <Grid container xs={8}>
                            <Grid item xs={12}>
                                <EditableContainer Component={RedactorField} handlefn={onSaveDescription(props, issue)}>
                                    {typeof issue.description === "string" && issue.description.length != 0
                                        ? issue.description : "<em>Double click to add a description...</em>"
                                    }
                                </EditableContainer>
                            </Grid>
                            <Grid item xs={12}>
                                <Divider my={8} />
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
                            <Grid item xs={2}>
                                Related Issues
                                <NewIssueLinkDialog issueId={issueHelper.buildExternalKey(issue)}/>
                            </Grid>
                            <Grid item xs={10}>
                                <RelatedIssuesComponent issue={issue}/>
                            </Grid>
                            <Grid item xs={2}>
                                Custom Fields
                            </Grid>
                            <Grid container xs={10}>
                                <CustomFieldsComponent issue={issue} dispatch={props.dispatch}/>
                            </Grid>
                        </Grid>
                        <Grid container xs={1} />
                        <Grid container xs={3}>
                            <Grid container xs={12}>
                                <Grid item xs={12}>
                                    <SectionTitle>Status</SectionTitle>
                                </Grid>
                                <Grid item xs={12}>
                                    <StatusPicker issue={issue} workflowTransitions={workflowTransitions} props={props} onTransitionIssue={onTransitionIssue}/>
                                </Grid>
                                <Grid item xs={12}>
                                    <SectionTitle>Type</SectionTitle>
                                </Grid>
                                <Grid item xs={12}>
                                    <IssueTypePicker postText={<KeyboardArrowDownIcon />} preText={""} issueTypeId={issue.issueTypeId} projectKey={issue.projectKey} onClickEvent={onSaveIssueType(props,issue)}/>
                                </Grid>
                                <Grid item xs={12}>
                                    <SectionTitle>Priority</SectionTitle>
                                </Grid>
                                <Grid item xs={12}>
                                    <IssuePriorityPicker preText={""} postText={<KeyboardArrowDownIcon />} issuePriorityId={issue.issuePriorityId} projectKey={issue.projectKey} onClickEvent={onSavePriority(props, issue)}/>
                                </Grid>
                                <Divider my={6} />
                                <Grid item xs={12}>
                                    <SectionTitle>Assignee</SectionTitle>
                                </Grid>
                                <Grid item xs={12}>
                                    <EditablePeopleField userId={issue.assigneeId} handleFn={onSaveAssignee(props, issue)} clickable={isAuthenticated()} />
                                </Grid>
                                <Grid item xs={12}>
                                    <SectionTitle>Reporter</SectionTitle>
                                </Grid>
                                <Grid item xs={12}>
                                    <EditablePeopleField userId={issue.createdByUserId} handleFn={onSaveReporter(props,issue)} clickable={isAuthenticated()}/>
                                </Grid>
                                <Divider my={6} />
                                {issue.createdDate &&
                                <React.Fragment>
                                    <Grid item xs={12}>
                                        <SectionTime>
                                            Created <RoundTimeAgo date={new Date(issue.createdDate)} />
                                        </SectionTime>

                                    </Grid>
                                </React.Fragment>
                                }
                                {issue.lastModifiedDate &&
                                <React.Fragment>
                                    <Grid item xs={12}>
                                        <SectionTime>
                                            Updated <RoundTimeAgo date={new Date(issue.lastModifiedDate)} />
                                        </SectionTime>
                                    </Grid>
                                </React.Fragment>
                                }
                            </Grid>
                        </Grid>
                    </Grid>
                </CardContent>
            </Card>
            {/*{isAuthenticated() &&*/}
            {/*<Card mb={6}>*/}
            {/*    <CardContent>*/}
            {/*        <Typography variant="h6">*/}
            {/*            Attachments*/}
            {/*        </Typography>*/}
            {/*        <DropzoneArea showFileNamesInPreview={true} showFileNames={true}/>*/}
            {/*    </CardContent>*/}
            {/*</Card>*/}
            {/*}*/}
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