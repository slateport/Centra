import {initConstants} from "../constants/init";

export const initActions = {
    loadInit,
};

function loadInit() {
    return async dispatch => {
        let response = await fetch('/api/init')
        if (response.ok) {
            dispatch(success(await response.json()))
        } else {
            dispatch(error(response))
        }
    }

    function success(initDto) { return { type: initConstants.INIT_SUCCESS, initDto } }
    function error(response) { return { type: initConstants.INIT_FAILURE, response } }
}