import React from "react";
import {Helmet} from "react-helmet";
import AuthLayout from "../../layouts/Auth";
import styled from "styled-components";
import {
    Avatar,
    Button as MuiButton,
    FormControl,
    Input,
    InputLabel,
    Paper,
    TextField,
    Typography
} from "@material-ui/core";
import {MuiButtonSpacingType} from "../../types/types";
import {spacing} from "@material-ui/system";
import {installActions} from "../../actions/install";
import {connect} from "react-redux";

const Button = styled(MuiButton)<MuiButtonSpacingType>(spacing);

const Wrapper = styled(Paper)`
  padding: ${props => props.theme.spacing(6)}px;
`;

class InstallationPage extends React.Component<any, any> {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            licenseKey: '',
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
        const { username, password, licenseKey } = this.state;
        const { dispatch } = this.props;
        if (username && password && licenseKey) {
            dispatch(installActions.completeInstallation(username, password, licenseKey));

        }
    }

    render() {
        const { username, password, licenseKey, install } = this.state;

        if (install) {
            this.props.history.push("/")
        } else {
            return (
                <React.Fragment>
                    <AuthLayout>
                        <Wrapper>
                            <Helmet title="Installation" />
                            <Typography component="h1" variant="h4" align="center" gutterBottom>
                                Welcome to Conductor!
                            </Typography>
                            <Typography component="h2" variant="body1" align="center">
                                Enter a few details to complete installation
                            </Typography>
                            <form>
                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="email">Username</InputLabel>
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
                                <FormControl margin="normal" required fullWidth>
                                    <TextField
                                        label="License Key"
                                        id="licenseKey"
                                        multiline
                                        rowsMax="4"
                                        name="licenseKey"
                                        value={licenseKey}
                                        onChange={this.handleChange}
                                    />
                                </FormControl>
                                <Button
                                    component={Button}
                                    to="/"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    mb={2}
                                    onClick={this.handleSubmit}
                                >
                                    Complete installation
                                </Button>
                            </form>
                        </Wrapper>
                    </AuthLayout>
                </React.Fragment>
            );
        }
    }
}

function mapStateToProps(state) {
    const { alert, init, install } = state;
    return {
        alert, init, install
    };
}

const connectedInstallationPage = connect(mapStateToProps)(InstallationPage);
export { connectedInstallationPage as InstallationPage };