import { projectConstants } from '../constants'

export function project (state = {}, action: any) {
  switch (action.type) {
    case projectConstants.GET_SUCCESS:
      return {
        ...action.projectDto
      }
    case projectConstants.GET_FAILURE:
      return {
        error: action.error
      }
    default:
      return state
  }
}
