import { push } from 'react-router-redux'

import { install as installService } from "../services";
import {installConstants} from "../constants";
import { alertActions } from './alert';
import { initActions } from "./init";

export const installActions = {
    completeInstallation
};

function completeInstallation(username: string, password: string, licenseKey: string, ) {
    return dispatch => {
        installService.completeInstallation(username, password, licenseKey)
            .then(
                response => {
                    dispatch(success(response))
                    dispatch(alertActions.success("Installation completed"))
                    dispatch(initActions.loadInit())

                    dispatch(push('/'))
                },
                response => {
                    console.log(response)
                    dispatch(alertActions.error(response || "Unexpected error"))
                    dispatch(failure(response))
                }
            )
    }

    function success(response) { return { type: installConstants.INSTALL_SUCCESS } }
    function failure(error) { return { type: installConstants.INSTALL_FAILURE, error } }
}
