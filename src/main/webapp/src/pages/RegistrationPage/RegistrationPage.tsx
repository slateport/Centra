import React from "react";
import {connect} from "react-redux";
import Init from "../../domain/Init";
import styled from "styled-components";
import {Button as MuiButton, FormControl, Input, InputLabel, Paper, Typography} from "@material-ui/core";
import {MuiButtonSpacingType} from "../../types/types";
import {spacing} from "@material-ui/system";
import {Helmet} from "react-helmet";
import {Redirect} from "react-router-dom";
import AuthLayout from "../../layouts/Auth";
import {user as userService} from "../../services";
import {isAuthenticated} from "../../helpers";
import {alertActions} from "../../actions";

interface RegistrationPageProps {
    init: Init,
    dispatch: Function
}

const Button = styled(MuiButton)<MuiButtonSpacingType>(spacing);

const Wrapper = styled(Paper)`
  padding: ${props => props.theme.spacing(6)}px;

  ${props => props.theme.breakpoints.up("md")} {
    padding: ${props => props.theme.spacing(10)}px;
  }
`;

class RegistrationPage extends React.Component<RegistrationPageProps, any> {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            emailAddress: '',
            displayName: '',
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        e.preventDefault();

        this.setState({ submitted: true });
        const { username, password, displayName, emailAddress } = this.state;
        const { dispatch } = this.props;
        if (username && password && displayName && emailAddress) {
            dispatch(alertActions.clear())
            userService.registerUser(username, password, displayName, emailAddress)
                .then(
                    () => dispatch(alertActions.success("Registration successful. You can now login")),
                    (json) => json.then(result => dispatch(alertActions.error(
                        "Registration failed. Please check your details are correct. " + result.message
                    )))
                )
        }
    }

    render() {
        if (isAuthenticated()){
            return (
                <Redirect to={'/'} />
            )
        }

        if (!this.props.init.registrationEnabled) {
            return (
                <React.Fragment>
                    Registration is disabled
                </React.Fragment>
            )
        }

        const { username, password, displayName, emailAddress} = this.state;

        return (
            <AuthLayout>
                <Wrapper>
                    <Helmet title="Sign Up" />
                    <div>
                        <Typography component="h2" variant="h4" align="center" gutterBottom>
                            Sign up
                        </Typography>
                        <form>
                            <FormControl margin="normal" required fullWidth onSubmit={this.handleSubmit}>
                                <InputLabel htmlFor="emailAddress">Email</InputLabel>
                                <Input
                                    id="emailAddress"
                                    name="emailAddress"
                                    autoComplete="emailAddress"
                                    autoFocus
                                    value={emailAddress}
                                    onChange={this.handleChange}
                                />
                            </FormControl>
                            <FormControl margin="normal" required fullWidth onSubmit={this.handleSubmit}>
                                <InputLabel htmlFor="displayName">Full name</InputLabel>
                                <Input
                                    id="displayName"
                                    name="displayName"
                                    autoComplete="name"
                                    value={displayName}
                                    onChange={this.handleChange}
                                />
                            </FormControl>
                            <FormControl margin="normal" required fullWidth onSubmit={this.handleSubmit}>
                                <InputLabel htmlFor="username">Username</InputLabel>
                                <Input
                                    id="username"
                                    name="username"
                                    autoComplete="username"
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
                            <Button
                                component={Button}
                                fullWidth
                                variant="contained"
                                color="primary"
                                onClick={this.handleSubmit}
                            >
                                Create account
                            </Button>
                        </form>
                    </div>
                </Wrapper>
            </AuthLayout>
        )
    }
}

function mapStateToProps (state) {
    const { init } = state
    return { init }
}

const connectedRegistrationPage = connect(mapStateToProps)(RegistrationPage)
export { connectedRegistrationPage as RegistrationPage }