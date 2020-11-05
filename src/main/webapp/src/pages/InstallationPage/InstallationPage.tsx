import React from "react";
import {Helmet} from "react-helmet";

class InstallationPage extends React.Component<any, any> {

    render() {
        return (
            <React.Fragment>
                <Helmet title="Install Conductor" />
                <h1>Installation!</h1>
            </React.Fragment>
        );
    }
}

export { InstallationPage };