import { issueConstants } from '../constants'

export function issue (state = {}, action: any) {
  switch (action.type) {
    case issueConstants.GET_SUCCESS:
      return {
        ...action.issueDto
      }
    case issueConstants.GET_FAILURE:
      return {
        error: action.error
      }
    case issueConstants.GET_COMMENTS_SUCCESS:
    return {
      ...state,
      comments: action.comments
    }
    case issueConstants.GET_COMMENTS_FAILURE:
      return {
        ...state
      }
    default:
      return state
  }
}
