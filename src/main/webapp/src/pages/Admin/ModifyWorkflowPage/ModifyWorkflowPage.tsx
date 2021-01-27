import {connect} from "react-redux";
import {Card, CardContent, Divider as MuiDivider, Typography} from "@material-ui/core";
import React from "react";
import {Helmet} from "react-helmet";
import AdminMenu from "../AdminMenu/AdminMenu";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import WorkflowApplication from "../../../components/workflow/WorkflowApplication";
import {workflow as workflowService} from "../../../services";
import {alertActions} from "../../../actions";

const Divider = styled(MuiDivider)(spacing)

function workflowOptions (workflowId) {
    return {
        workflowId
    }
}

class ModifyWorkflowPage extends React.Component<any, any> {
    constructor(props) {
        super(props);

        this.state = {
            workflow: undefined
        }
    }

    componentDidMount() {
        const { match: { params } } = this.props;

        workflowService.getWorkflow(params.workflowId)
            .then(workflow => this.setState({ workflow }))
            .catch(_ => this.props.dispatch(alertActions.error("Failed to load workflow.")))
    }

    render() {
        if (!this.props.init.user?.admin) {
            return (
                <Typography>You are not authorised to view this page.</Typography>
            )
        }

        if (this.state.workflow == undefined) {
            return (
                <Typography>Loading workflow...</Typography>
            )
        }

        return (
            <React.Fragment>
                <Helmet title="Workflows" />
                <AdminMenu>
                    <Typography variant="h3" gutterBottom display="inline">
                        Manage Workflows
                    </Typography>
                    <Divider my={6} />
                    <Card>
                        <CardContent>
                            <WorkflowApplication options={workflowOptions(this.state.workflow.id)}/>
                        </CardContent>
                    </Card>
                </AdminMenu>
            </React.Fragment>
        )
    }
}

function mapStateToProps (state) {
    const { init } = state
    return { init }
}

const connected = connect(mapStateToProps)(ModifyWorkflowPage)
export { connected as ModifyWorkflowPage }