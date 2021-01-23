import React from 'react'
import { connect } from 'react-redux'
import {
    Button,
    Card,
    CardContent,
    Divider as MuiDivider,
    Grid,
    Table,
    TableBody,
    TableContainer, TextField,
    Typography
} from "@material-ui/core";
import {Helmet} from "react-helmet";
import AdminMenu from "../AdminMenu/AdminMenu";
import { user as userService } from '../../../services'
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import {alertActions} from "../../../actions";

const Divider = styled(MuiDivider)(spacing)

class ModifyUserPage extends React.Component<any, any>{
    constructor(props) {
        super(props);

        this.state = {
            user: {}
        }

        this.handleUserValChange = this.handleUserValChange.bind(this)
        this.updateUser = this.updateUser.bind(this)
    }

    componentDidMount() {
        const { match: { params } } = this.props;
        userService.getUser(params.internalId)
            .then(user => this.setState({ user }))
            .catch(_ => this.setState({ user: {} }))
    }

    handleUserValChange(e) {
        const { name, value } = e.target;
        const user = this.state.user
        user[name] = value
        this.setState({ user });
    }

    updateUser(){
        const user = this.state.user

        userService.updateUser(user)
            .then  (_ => this.props.dispatch(alertActions.success("Successfully updated user")))
            .catch (_ => this.props.dispatch(alertActions.error("Failed to update user")))
    }

    render() {
        if (!this.props.init.user?.admin) {
            return (
                <Typography>You are not authorised to view this page.</Typography>
            )
        }

        if (Object.keys(this.state.user).length === 0) {
            return (
                <Typography>User does not exist.</Typography>
            )
        }

        return (
            <React.Fragment>
                <Helmet title="Manage User" />
                <AdminMenu>
                    <Typography variant="h3" gutterBottom display="inline">
                        Manage User: {this.state.user.displayName}
                    </Typography>
                    <Divider my={6} />
                    <Card>
                        <CardContent>
                            <TableContainer>
                                <Table>
                                    <TableBody>
                                        <TableRow key="username">
                                            <TableCell>Username</TableCell>
                                            <TableCell>
                                                {this.state.user.username}
                                            </TableCell>
                                        </TableRow>
                                        <TableRow key="displayName">
                                            <TableCell>Display Name</TableCell>
                                            <TableCell>
                                                <TextField name={'displayName'} defaultValue={this.state.user.displayName} onChange={this.handleUserValChange}/>
                                            </TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </TableContainer>
                            <Grid container justify="flex-end" style={{paddingTop: 20}}>
                                <Button variant="outlined" color="primary" onClick={this.updateUser}>
                                    Save
                                </Button>
                            </Grid>
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

const connectedPage = connect(mapStateToProps)(ModifyUserPage)
export { connectedPage as ModifyUserPage }
