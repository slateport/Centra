import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { Helmet } from 'react-helmet';

class HomePage extends React.Component<any, any> {

    render() {
        return (
                <React.Fragment>
                    <Helmet title="Dashboard" />
                    <h1>Hi !</h1>
                    <p>
                        <Link to="/login">Logout</Link>
                    </p>
                </React.Fragment>
        );
    }
}

function mapStateToProps(state) {
    const { users, authentication } = state;
    const { user } = authentication;
    return {
        user,
        users
    };
}

const connectedHomePage = connect(mapStateToProps)(HomePage);
export { connectedHomePage as HomePage };
