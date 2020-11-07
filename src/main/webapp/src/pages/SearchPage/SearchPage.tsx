import React from "react"
import {
    Button as MuiButton,
    Card as MuiCard,
    Grid,
    TextField,
    Typography,
    CardContent,
    List,
    ListItem,
    ListItemText,
    Divider
} from "@material-ui/core";
import {connect} from "react-redux";
import {Helmet} from "react-helmet";
import IssueComponent from "../ViewIssuePage/components/IssueComponent";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import {MuiButtonSpacingType} from "../../types/types";
import {issueActions, userActions} from "../../actions";
import {searchActions} from "../../actions/search";
import {projectActions} from "../../actions/project";

const Button = styled(MuiButton)<MuiButtonSpacingType>(spacing);
const Card = styled(MuiCard)(spacing);

class SearchPage extends React.Component<any, any>{

    constructor(props) {
        super(props);

        this.state = {
            cql: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSearchSubmit = this.handleSearchSubmit.bind(this);
        this.loadIssue.bind(this)
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSearchSubmit(e){
        const { cql } = this.state
        this.props.dispatch(searchActions.searchIssue(cql));
    }

    loadIssue(externalId) {
        this.props.dispatch(issueActions.getIssue(externalId))
            .then(() => {
                this.props.dispatch(projectActions.getProject(this.props.issue.projectId))
                this.props.dispatch(issueActions.getIssueComments(externalId))
            })
    }

    render() {
        return (
            <React.Fragment>
                <Helmet title="Search" />
                <Card>
                    <CardContent>
                        <Typography variant="h3" gutterBottom display="inline">
                            Search
                        </Typography>
                        <Grid container xs={12}>
                            <Grid item xs={10}>
                                <TextField
                                    fullWidth
                                    multiline
                                    type="text"
                                    name={"cql"}
                                    onChange={this.handleChange}
                                />
                            </Grid>
                            <Grid item xs={1}>
                                <Button
                                    component={Button}
                                    variant="contained"
                                    color="primary"
                                    onClick={this.handleSearchSubmit}
                                >
                                    Search
                                </Button>
                            </Grid>
                        </Grid>
                        <Grid container xs={12}>
                            <Grid item xs={3}>
                                <List component="nav">
                                    {this.props.search.issues &&
                                    <ul>
                                        {this.props.search.issues.map((issue, _) =>
                                            <React.Fragment>
                                                <ListItem
                                                    button
                                                    onClick={() => this.loadIssue(issue.projectKey +'-'+issue.externalId)}
                                                    key={issue.id}
                                                >
                                                    <ListItemText primary={issue.title} />
                                                </ListItem>
                                                <Divider />
                                            </React.Fragment>
                                        )}
                                    </ul>
                                    }
                            </List>
                            </Grid>
                            <Grid item xs={9}>
                                {this.props.issue.title != undefined  &&
                                    <IssueComponent issue={this.props.issue} project={this.props.project} props={this.props} />
                                }
                            </Grid>
                        </Grid>
                    </CardContent>
                </Card>
            </React.Fragment>
        )
    }
}

function mapStateToProps(state) {
    const { init, issue, project, search } = state;
    return {
        init, issue, project, search
    };
}

const connectedViewIssuePage = connect(mapStateToProps)(SearchPage);
export { connectedViewIssuePage as SearchPage };