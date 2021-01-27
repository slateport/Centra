import React from 'react'
import {connect} from "react-redux";
import {Card, CardContent, Divider as MuiDivider, Link, Typography} from "@material-ui/core";
import {Helmet} from "react-helmet";
import AdminMenu from "../AdminMenu/AdminMenu";
import {CellParams, ColDef, DataGrid} from "@material-ui/data-grid";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import {Link as RouterLink} from "react-router-dom";
import EditIcon from "@material-ui/icons/Edit";
import { workflow as workflowService } from '../../../services'
import {alertActions} from "../../../actions";

const Divider = styled(MuiDivider)(spacing)

const columns: ColDef[] = [
    { field: 'name', headerName: 'Workflow Name', width: 600 },
    {
        field: "",
        headerName: "Actions",
        disableClickEventBubbling: true,
        renderCell: (params: CellParams) => {
            return <Link component={RouterLink} to={`/admin/workflows/${params.row.id}`}><EditIcon /></Link>;
        }
    }
];

class WorkflowsPage extends React.Component<any, any> {
    constructor(props) {
        super(props);

        this.state = {
            workflows: undefined
        }
    }

    componentDidMount() {
        workflowService.listWorkflows()
            .then(workflows => this.setState({ workflows }))
            .catch(_ => this.props.dispatch(alertActions.error("Failed to load workflows.")))
    }

    render() {
        if (!this.props.init.user?.admin) {
            return (
                <Typography>You are not authorised to view this page.</Typography>
            )
        }

        if (this.state.workflows == undefined) {
            return (
                <Typography>Loading workflows...</Typography>
            )
        }

        return (
            <React.Fragment>
                <Helmet title="Users" />
                <AdminMenu>
                    <Typography variant="h3" gutterBottom display="inline">
                        Manage Workflows
                    </Typography>
                    <Divider my={6} />
                    <Card>
                        <CardContent>
                            <div style={{ height: 300, width: '100%' }}>
                                <DataGrid rows={this.state.workflows} columns={columns} />
                            </div>
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

const connectedWorkflowPage = connect(mapStateToProps)(WorkflowsPage)
export { connectedWorkflowPage as WorkflowPage }