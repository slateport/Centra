import {Redirect, Route, Switch} from "react-router-dom";
import {RegistrationPage} from "../pages/RegistrationPage";
import {LoginPage} from "../pages/LoginPage";
import {InstallationPage} from "../pages/InstallationPage";
import {ViewIssuePage} from "../pages/ViewIssuePage";
import {SearchPage} from "../pages/SearchPage";
import {HomePage} from "../pages/HomePage";
import {ProjectsPage} from "../pages/Admin/ProjectsPage";
import {UsersPage} from "../pages/Admin/UsersPage";
import {ModifyUserPage} from "../pages/Admin/ModifyUserPage";
import {GeneralPage} from "../pages/Admin/GeneralPage";
import React from "react";
import {PrivateRoute} from "../components";

const NoMatch = ({ location }) => {
    const pathName = location.pathname || location.location.pathname
    return (
        <h3>No match for <code>{pathName}</code></h3>
    )
}

const Routes = ({init}) => {
    const Routing = (!init.instancePrivate) ? Route : PrivateRoute
    console.log(init)
    return (
        <Switch>
            <Route path="/register" component={RegistrationPage} />
            <Route path="/login" component={LoginPage} />
            <Route path="/install" component={InstallationPage} />
            <Routing path="/browse/:externalId" component={ViewIssuePage} />
            <Routing path="/search" component={SearchPage} />
            <Routing exact path="/" component={HomePage} />
            <PrivateRoute exact path="/admin/projects" component={ProjectsPage} />
            <PrivateRoute exact path="/admin/users" component={UsersPage} />
            <PrivateRoute exact path="/admin/users/:internalId" component={ModifyUserPage} />
            <PrivateRoute exact path="/admin/" component={GeneralPage} />

            <Route component={NoMatch} />
            <Redirect to="/page-not-found" />
        </Switch>
    )
}

export { Routes }