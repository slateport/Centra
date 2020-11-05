import React, { Component } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useSelector } from "react-redux";

function PrivateRoute ({component: Component, ...rest }) {
    const stateFn = state => state.init;
    const init = useSelector(stateFn)

    const isInstalled = init.installationComplete || true;
    const isUserStored = localStorage.getItem('user');

    console.log(isInstalled);

    return (
        <Route {...rest} render={props => (
            isInstalled
                ? (isUserStored) ? <Component {...props} />
                : <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
                :  <Redirect
                    to={{
                        pathname: "/install",
                        state: { from: props.location }
                    }}
                />
        )} />
    )
}

export { PrivateRoute }