import React from 'react'
import { connect } from 'react-redux'
import {Card, CardContent, Divider as MuiDivider, Typography} from '@material-ui/core'
import { Helmet } from 'react-helmet'
import AdminMenu from "../AdminMenu/AdminMenu";
import styled from "styled-components";
import {spacing} from "@material-ui/system";

const Divider = styled(MuiDivider)(spacing)

class GeneralPage extends React.Component<any, any> {

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