import React from 'react'

import { ThemeProvider } from 'styled-components'
import {Redirect, Route, BrowserRouter as Router, Switch, useHistory} from 'react-router-dom'
import { connect } from 'react-redux'
import { Helmet } from 'react-helmet'

import { history } from '../helpers'
import { PrivateRoute } from '../components'
import { HomePage } from '../pages/HomePage'
import { ProjectsPage } from '../pages/Admin/ProjectsPage'
import { LoginPage } from '../pages/LoginPage'
import { StylesProvider } from '@material-ui/styles'
import { ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles'
import { Alert } from '@material-ui/lab'

import { alertActions, initActions } from '../actions'

import theme from '../theme'
import { InstallationPage } from '../pages/InstallationPage'
import { ViewIssuePage } from '../pages/ViewIssuePage'
import { SearchPage } from '../pages/SearchPage'
import { ApplicationLayout } from '../layouts/ApplicationLayout'

import $ from 'jquery'
import { RegistrationPage } from '../pages/RegistrationPage'
import {GeneralPage} from "../pages/Admin/GeneralPage";

$.fn.draggable = () => {}
$.fn.droppable = () => {}

const NoMatch = ({ location }) => {
    const pathName = location.pathname || location.location.pathname
    return (
        <h3>No match for <code>{pathName}</code></h3>
    )
}

class App extends React.Component<any, any> {
  constructor (props) {
    super(props)

    const { dispatch } = this.props
    dispatch(initActions.loadInit())

    history.listen(({ location, action }) => {
      dispatch(alertActions.clear())
    })
  }

  render () {
    const { alert, init } = this.props
    if (init && init.installationComplete === false && window.location.href !== '/install') {
      return (
                <React.Fragment>
                    <Helmet titleTemplate="%s | Centra" defaultTitle="Centra"/>
                    <StylesProvider injectFirst>
                        <MuiThemeProvider theme={theme[0]}>
                            <ThemeProvider theme={theme[0]}>
                                {alert.message &&
                                <Alert variant="filled" severity={alert.type}>{alert.message}</Alert>
                                }
                                <InstallationPage />
                            </ThemeProvider>
                        </MuiThemeProvider>
                    </StylesProvider>
                </React.Fragment>

      )
    } else {
      const Routing = (!init.instancePrivate) ? Route : PrivateRoute
      return (
                <React.Fragment>
                    <Helmet titleTemplate="%s | Centra" defaultTitle="Centra"/>
                    <StylesProvider injectFirst>
                        <Router>
                        <MuiThemeProvider theme={theme[0]}>
                            <ThemeProvider theme={theme[0]}>
                                {alert.message &&
                                <Alert variant="filled" severity={alert.type}>{alert.message}</Alert>
                                }
                                <ApplicationLayout props={this.props} init={init}>
                                        <Switch>
                                            <Route path="/register" component={RegistrationPage} />
                                            <Route path="/login" component={LoginPage} />
                                            <Route path="/install" component={InstallationPage} />
                                            <Routing path="/browse/:externalId" component={ViewIssuePage} />
                                            <Routing path="/search" component={SearchPage} />
                                            <Routing exact path="/" component={HomePage} />
                                            <Routing exact path="/admin/projects" component={ProjectsPage} />
                                            <Routing path="/admin/" component={GeneralPage} />

                                            <Route component={NoMatch} />
                                            <Redirect to="/page-not-found" />
                                        </Switch>
                                </ApplicationLayout>
                            </ThemeProvider>
                        </MuiThemeProvider>
                        </Router>
                    </StylesProvider>
                </React.Fragment>
      )
    }
  }
}

function mapStateToProps (state) {
  const { alert, init } = state
  return {
    alert, init
  }
}

const connectedApp = connect(mapStateToProps)(App)
export { connectedApp as App }
