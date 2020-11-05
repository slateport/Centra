import { initConstants } from '../constants';

export function init(state = {}, action: any) {
    switch (action.type) {
        case initConstants.INIT_REQUEST:
            return {
                loading: true
            };
        case initConstants.INIT_SUCCESS:
            return {
                ...action.initDto
            };
        case initConstants.INIT_FAILURE:
            return {
                error: action.error
            };
        default:
            return state
    }
}