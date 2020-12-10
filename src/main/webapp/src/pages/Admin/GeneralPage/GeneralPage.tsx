import React from 'react'
import { connect } from 'react-redux'
import {Card, CardContent, Divider as MuiDivider, Table, TableBody, TableContainer, Typography} from '@material-ui/core'
import { Helmet } from 'react-helmet'
import AdminMenu from "../AdminMenu/AdminMenu";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import Switcher from "../../../components/switcher";
import {settings as settingsService } from './../../../services'
import EditableContainer from "../../../components/EditableContainer";
import Field from "../../../components/StandardTextField";
import {initActions} from "../../../actions";

const Divider = styled(MuiDivider)(spacing)

class GeneralPage extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            settings: []
        }

        this.onToggleSwitch = this.onToggleSwitch.bind(this)
        this.onChangeTextField = this.onChangeTextField.bind(this)
    }

    componentDidMount() {
        settingsService.getAllSettings().then(
            settings => this.setState({settings})
        )
    }

    onToggleSwitch(event: React.ChangeEvent<HTMLInputElement>) {
        const currentState = this.state.settings[event.target.name]

        if (currentState == "true"){
            this.setState({settings: {...this.state.settings, [event.target.name]: "false"}})
            settingsService.saveSetting(event.target.name, "false")
        } else {
            this.setState({settings: {...this.state.settings, [event.target.name]: "true"}})
            settingsService.saveSetting(event.target.name, "true")
        }
        this.props.dispatch(initActions.loadInit())
    }

    onChangeTextField(fieldName) {
        return e => {
            // e.children is the value
            this.setState({settings: {...this.state.settings, [fieldName]: e.children}})
            settingsService.saveSetting(fieldName, e.children)
            this.props.dispatch(initActions.loadInit())
        }
    }

    render() {
        if (!this.props.init.user?.admin) {
            return (
                <Typography>You are not authorised to view this page.</Typography>
            )
        }

        return (
            <React.Fragment>
                <Helmet title="General Settings" />
                <AdminMenu>
                    <Typography variant="h3" gutterBottom display="inline">
                        General Settings
                    </Typography>
                    <Divider my={6} />
                    <Card>
                        <CardContent>
                            <TableContainer>
                                <Table>
                                    <TableBody>
                                        <TableRow key="instance.name">
                                            <TableCell>Instance name</TableCell>
                                            <TableCell>
                                                <EditableContainer handlefn={this.onChangeTextField('instance.public_name')} Component={Field}>
                                                    {this.state.settings["instance.public_name"] ?? ""}
                                                </EditableContainer>
                                                </TableCell>
                                        </TableRow>
                                        <TableRow key="instance.private">
                                            <TableCell>Private Mode</TableCell>
                                            <TableCell>
                                                <Switcher name='instance.private' onChange={this.onToggleSwitch} checked={this.state.settings["instance.private"] == "true"}/>
                                            </TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </TableContainer>
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

const connectedGeneralPage = connect(mapStateToProps)(GeneralPage)
export { connectedGeneralPage as GeneralPage }