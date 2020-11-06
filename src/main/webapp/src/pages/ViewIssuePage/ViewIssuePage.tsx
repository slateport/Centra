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
import styled from "styled-components";
import { spacing } from "@material-ui/system";
import { NavLink as RouterNavLink, LinkProps} from "react-router-dom";
import {Helmet} from "react-helmet";
import {projectActions} from "../../actions/project";
import IssueComment from "./components/IssuesComponent";
import PeopleField from "./components/PeopleField";
import EditableContainer from "../../components/EditableContainer";

const Breadcrumbs = styled(MuiBreadcrumbs)(spacing);

const NavLink = React.forwardRef<LinkProps, any>((props, ref) => (
    <RouterNavLink innerRef={ref} {...props} />
));

const Card = styled(MuiCard)(spacing);
const Divider = styled(MuiDivider)(spacing);

class ViewIssuePage extends React.Component<any, any> {
    constructor(props) {
        super(props);

        const { match: { params } } = this.props;
        props.dispatch(issueActions.getIssue(params.externalId))
        .then(() => {
            props.dispatch(projectActions.getProject(this.props.issue.projectId))
            props.dispatch(issueActions.getIssueComments(params.externalId))
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

        return (
            <React.Fragment>
                <Helmet title={this.props.issue.title} />
                <Typography variant="h3" gutterBottom display="inline">
                    {this.props.issue.title}
                </Typography>
                <Breadcrumbs aria-label="Breadcrumb" mt={2}>
                    <Link component={NavLink} exact to="/">
                        {this.props.project.projectName}
                    </Link>
                    <Typography>{this.props.issue.title}</Typography>
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
                            <Typography>{this.props.issue.issuePriority}</Typography>
                        </Grid>
                    </Grid>
                    <Grid container xs={1} />
                    <Grid container xs={3}>
                        <Grid container xs={12}>
                            <Grid item xs={6}>
                               Assignee:
                            </Grid>
                            <Grid item xs={6}>
                                <PeopleField userId={this.props.issue.assignee} />
                            </Grid>
                            <Grid item xs={6}>
                                Reporter"
                            </Grid>
                            <Grid item xs={6}>
                                <PeopleField userId={this.props.issue.createdByUserId} />
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
                        <EditableContainer>
                            {this.props.issue.description}
                        </EditableContainer>
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