import React from 'react'
import { connect } from 'react-redux'
import { Helmet } from 'react-helmet'
import { userActions } from '../../actions'
import styled from 'styled-components'
import AuthLayout from '../../layouts/Auth'

import {
  Button as MuiButton,
  Checkbox,
  FormControl,
  FormControlLabel,
  Input,
  InputLabel,
  Link,
  Paper,
  Typography
} from '@material-ui/core'
import { spacing } from '@material-ui/system'
import { MuiButtonSpacingType } from '../../types/types'

const Button = styled(MuiButton)<MuiButtonSpacingType>(spacing)

const Wrapper = styled(Paper)`
  padding: ${props => props.theme.spacing(6)}px;

  ${props => props.theme.breakpoints.up('md')} {
    padding: ${props => props.theme.spacing(10)}px;
  }
`

class LoginPage extends React.Component<any, any> {
  constructor (props) {
    super(props)

    // reset login status
    this.props.dispatch(userActions.logout())

    this.state = {
      username: '',
      password: '',
      submitted: false
    }

    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleChange (e) {
    const { name, value } = e.target
    this.setState({ [name]: value })
  }

  handleSubmit (e) {
    e.preventDefault()

    this.setState({ submitted: true })
    const { username, password } = this.state
    const { dispatch } = this.props
    if (username && password) {
      dispatch(userActions.login(username, password))
    }
  }

  render () {
    const { username, password } = this.state
    const createNewAccountBtn = !this.props.init.registrationEnabled ? ''
      : <Button
                component={Button}
                href={'/register'}
                fullWidth
                color="primary"
            >
                Create account
        </Button>

    return (
            <AuthLayout>
            <Wrapper>
                <Helmet title="Sign In" />
                <div>
                    <Typography component="h2" variant="h4" align="center" gutterBottom>
                        Sign in to your account to continue
                    </Typography>
                    <form>
                        <FormControl margin="normal" required fullWidth onSubmit={this.handleSubmit}>
                            <InputLabel htmlFor="username">Username</InputLabel>
                            <Input
                                id="username"
                                name="username"
                                autoComplete="username"
                                autoFocus
                                value={username}
                                onChange={this.handleChange}
                            />
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel htmlFor="password">Password</InputLabel>
                            <Input
                                name="password"
                                type="password"
                                id="password"
                                autoComplete="current-password"
                                value={password}
                                onChange={this.handleChange}
                            />
                        </FormControl>
                        <FormControlLabel
                            control={<Checkbox value="remember" color="primary" />}
                            label="Remember me"
                        />
                        <Button
                            component={Button}
                            fullWidth
                            variant="contained"
                            color="primary"
                            onClick={this.handleSubmit}
                        >
                            Sign in
                        </Button>
                        <Button
                            component={Link}
                            to="/auth/reset-password"
                            fullWidth
                            color="primary"
                        >
                            Forgot password
                        </Button>
                        {createNewAccountBtn}
                    </form>
                </div>
            </Wrapper>
            </AuthLayout>
    )
  }
}

function mapStateToProps (state) {
  const { authentication, init } = state
  const loggingIn = authentication.loggingIn
  return {
    loggingIn,
    init
  }
}

const connectedLoginPage = connect(mapStateToProps)(LoginPage)
export { connectedLoginPage as LoginPage }
