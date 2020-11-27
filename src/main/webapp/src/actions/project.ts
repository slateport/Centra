import { projectConstants} from '../constants'
import { alertActions } from './alert'
import { project } from '../services'

export const projectActions = {
    getProject
}

function getProject (externalId: string) {
    return async dispatch => {
        await project.getProject(externalId)
            .then(response => dispatch(success(response)))
            .catch(e => {
                dispatch(error(e))
                dispatch(alertActions.error('Failed to fetch project.'))
            })

    }

    function success (projectDto) { return { type: projectConstants.GET_SUCCESS, projectDto } }
    function error (response) { return { type: projectConstants.GET_SUCCESS, response } }
}
