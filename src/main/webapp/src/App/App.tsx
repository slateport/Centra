import React from 'react'
import $ from 'jquery'
import theme from '../theme'
import { Routes } from "./Routes";
import styled, { ThemeProvider } from 'styled-components'
import { BrowserRouter as Router } from 'react-router-dom'
import { connect } from 'react-redux'
import { Helmet } from 'react-helmet'
import { StylesProvider } from '@material-ui/styles'
import { ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles'
import { Alert as MuiAlert } from '@material-ui/lab'
import { initActions } from '../actions'
import { InstallationPage } from '../pages/InstallationPage'
import { ApplicationLayout } from '../layouts/ApplicationLayout'

$.fn.draggable = () => {}
$.fn.droppable = () => {}

const Alert = styled(MuiAlert)`
z-index: 100;
position: relative;
`


class App extends React.Component<any, any> {
  constructor (props) {
    super(props)

    const { dispatch } = this.props
    dispatch(initActions.loadInit())
  }

  render () {
    const { alert, init } = this.props
    if (init && init.installationComplete === false && window.location.href !== '/install') {
      return (
                <React.Fragment>
                    <Helmet titleTemplate={"%s | Centra"} defaultTitle={'Centra'}/>
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
      const siteName = (init) ? init?.publicName : "Centra"
      return (
        <React.Fragment>
            <Helmet titleTemplate={"%s | " + siteName} defaultTitle={siteName}/>
            <StylesProvider injectFirst>
                <Router>
                <MuiThemeProvider theme={theme[0]}>
                    <ThemeProvider theme={theme[0]}>
                        {alert.message &&
                        <Alert variant="filled" severity={alert.type}>{alert.message}</Alert>
                        }
                        <ApplicationLayout dispatch={this.props.dispatch} init={init}>
                            <Routes init={init} />
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
