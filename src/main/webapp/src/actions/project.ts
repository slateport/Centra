import { projectConstants} from '../constants'
import { alertActions } from './alert'
import { project } from '../services'

export const projectActions = {
    getProject
}

function getProject (externalId: string) {
    return async dispatch => {
        const response = await project.getProject(externalId)
        if (response.ok) {
            dispatch(success(await response.json()))
        } else {
            dispatch(error(response))
            dispatch(alertActions.error('Failed to fetch project.'))
        }
    }

    function success (projectDto) { return { type: projectConstants.GET_SUCCESS, projectDto } }
    function error (response) { return { type: projectConstants.GET_SUCCESS, response } }
}
