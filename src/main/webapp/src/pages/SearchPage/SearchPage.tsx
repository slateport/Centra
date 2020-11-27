import "react-perfect-scrollbar/dist/css/styles.css";
import React from "react"
import {
    Button as MuiButton,
    Card as MuiCard,
    CardContent,
    Divider as MuiDivider,
    Grid,
    List as MuiList,
    ListItem,
    ListItemText,
    TextField,
    Typography,
} from "@material-ui/core";
import {connect} from "react-redux";
import {Helmet} from "react-helmet";
import IssueComponent from "../ViewIssuePage/components/IssueComponent";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import {MuiButtonSpacingType} from "../../types/types";
import {alertActions, issueActions} from "../../actions";
import {searchActions} from "../../actions/search";
import {projectActions} from "../../actions/project";
import PerfectScrollbar from "react-perfect-scrollbar";
import {issue} from "../../services";

const Button = styled(MuiButton)<MuiButtonSpacingType>(spacing);
const Card = styled(MuiCard)(spacing);

const List = styled(MuiList)`
  border: 1px solid rgba(0, 0, 0, 0.12);
`;

const IssueListContainer = styled.div`
  height: 75vh;
  position: relative;
  overflow: scroll;
`;

const IssueListContent = styled.div`
  position: relative;
`;

const Divider = styled(MuiDivider)(spacing);

class SearchPage extends React.Component<any, any>{
    private transitions = [];
    private urlCql;
    constructor(props) {
        super(props);

        this.state = {
            cql: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSearchSubmit = this.handleSearchSubmit.bind(this);
        this.loadIssue.bind(this)

        this.urlCql = new URLSearchParams(this.props.location.search).get('cql')

        if (this.urlCql) {
            this.setState({cql: this.urlCql})
            this.props.dispatch(searchActions.searchIssue(this.urlCql));
        }
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSearchSubmit(){
        const { cql } = this.state
        this.props.dispatch(searchActions.searchIssue(cql));
    }

    loadIssue(externalId) {
        this.props.dispatch(issueActions.getIssue(externalId))
            .then(() => {
                this.props.dispatch(projectActions.getProject(this.props.issue.projectId))
                this.props.dispatch(issueActions.getIssueComments(externalId))
                issue.getWorkflowTransitions(externalId)
                    .then(transitions => {this.transitions = transitions})
                    .catch(e => this.props.dispatch(alertActions.error("Failed to fetch transitions")))
            })
    }

    render() {
        return (
            <React.Fragment>
                <Helmet title="Search" />
                        <Typography variant="h3" display="block">
                            Search
                        </Typography>
                        <br />
                         <Divider my={6}/>
                        <br />
                        <Grid container spacing={6}>
                            <Grid item xs={12}>
                                <Card>
                                    <CardContent>
                                        <Grid container spacing={6}>
                                            <Grid item xs={10}>
                                                <TextField
                                                    fullWidth
                                                    multiline
                                                    type="text"
                                                    name={"cql"}
                                                    aria-valuetext={this.urlCql}
                                                    defaultValue={this.urlCql}
                                                    onChange={this.handleChange}
                                                />
                                            </Grid>
                                            <Grid item xs={1} />
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
                                    </CardContent>
                                </Card>
                            </Grid>
                        </Grid>
                        <br/>
                        <Grid container spacing={6}>
                            <Grid item xs={3} >
                                    {this.props.search.issues &&
                                    <Card>
                                    <CardContent>
                                        <IssueListContainer>
                                            <PerfectScrollbar component="div">
                                                <IssueListContent>
                                                    <List disablePadding>
                                                        {this.props.search.issues.map((issue, _) =>
                                                            <React.Fragment key={issue.id}>
                                                                <ListItem
                                                                    button
                                                                    onClick={() => this.loadIssue(issue.projectKey +'-'+issue.externalId)}
                                                                    key={issue.id}
                                                                >
                                                                    <ListItemText key={issue.id} primary={issue.title} secondary={issue.projectKey + '-' + issue.externalId} />
                                                                </ListItem>
                                                                <Divider />
                                                            </React.Fragment>
                                                        )}
                                                    </List>
                                                </IssueListContent>
                                            </PerfectScrollbar>
                                        </IssueListContainer>

                                    </CardContent>
                                    </Card>
                                    }
                            </Grid>
                            <Grid item xs={9}>
                                {this.props.issue.title != undefined  &&
                                <Card>
                                    <CardContent>
                                        <IssueComponent
                                            issue={this.props.issue}
                                            project={this.props.project}
                                            initialWorkflowTransitions={this.transitions}
                                            props={this.props} />

                                    </CardContent>
                                </Card>
                                }
                            </Grid>
                        </Grid>
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