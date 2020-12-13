import React from 'react'
import { connect } from 'react-redux'
import { Link as RouterLink } from 'react-router-dom'
import {
    Button,
    Card,
    CardContent,
    Divider as MuiDivider, Link,
    Typography
} from "@material-ui/core";
import {Helmet} from "react-helmet";
import AdminMenu from "../AdminMenu/AdminMenu";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import { user as userService } from '../../../services'
import {alertActions} from "../../../actions";
import { DataGrid, ColDef, CellParams } from '@material-ui/data-grid'
import EditIcon from '@material-ui/icons/Edit'

const Divider = styled(MuiDivider)(spacing)

const columns: ColDef[] = [
    { field: 'username', headerName: 'Username', width: 150 },
    { field: 'displayName', headerName: 'Display Name', width: 150 },
    {
        field: "",
        headerName: "Actions",
        disableClickEventBubbling: true,
        renderCell: (params: CellParams) => {
            return <Link component={RouterLink} to={`/admin/users/${params.row.id}`}><EditIcon /></Link>;
        }
    }
];


class UsersPage extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            users: []
        }
    }

    componentDidMount() {
        userService.getAllLite()
            .then(users => this.setState({ users }))
            .catch(_ => alertActions.error("Failed to load users"))
    }

    render() {
        if (!this.props.init.user?.admin) {
            return (
                <Typography>You are not authorised to view this page.</Typography>
            )
        }

        return (
            <React.Fragment>
                <Helmet title="Users" />
                <AdminMenu>
                    <Typography variant="h3" gutterBottom display="inline">
                        Manage Users
                    </Typography>
                    <Divider my={6} />
                    <Card>
                        <CardContent>
                            <div style={{ height: 300, width: '100%' }}>
                                <DataGrid rows={this.state.users} columns={columns} />
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

const connectedUsersPage = connect(mapStateToProps)(UsersPage)
export { connectedUsersPage as UsersPage }
