import React from 'react';
import styled from "styled-components";
import { Router, Route } from 'react-router-dom';
import { connect } from 'react-redux';
import { Helmet } from 'react-helmet';


import { history } from '../helpers';
import { alertActions } from '../actions';
import { PrivateRoute } from '../components';
import { HomePage } from '../HomePage';
import { LoginPage } from '../LoginPage';
import { StylesProvider } from "@material-ui/styles";
import { ThemeProvider as MuiThemeProvider } from "@material-ui/core/styles";
import { Alert as MuiAlert, AlertTitle } from '@material-ui/lab';
import { ThemeProvider } from "styled-components";
import { spacing } from "@material-ui/system";

import theme from "../theme";

const Alert = styled(MuiAlert)(spacing);

class App extends React.Component<any, any> {
    constructor(props) {
        super(props);

        const { dispatch } = this.props;
        history.listen((location, action) => {
            // clear alert on location change
            dispatch(alertActions.clear());
        });
    }

    render() {
        const { alert } = this.props;
        return (
            <React.Fragment>
                <Helmet titleTemplate="%s | Conductor"/>
                <StylesProvider injectFirst>
                    <MuiThemeProvider theme={theme[0]}>
                        <ThemeProvider theme={theme[0]}>
                            {alert.message &&
                                <Alert mb={4} severity={alert.type}>{alert.message}</Alert>
                            }
                            <Router history={history}>
                                <div>
                                    <PrivateRoute exact path="/" component={HomePage} />
                                    <Route path="/login" component={LoginPage} />
                                </div>
                            </Router>
                        </ThemeProvider>
                    </MuiThemeProvider>
                </StylesProvider>
            </React.Fragment>
        );
    }
}

function mapStateToProps(state) {
    const { alert } = state;
    return {
        alert
    };
}

const connectedApp = connect(mapStateToProps)(App);
export { connectedApp as App }; 