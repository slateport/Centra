import { searchConstants } from '../constants'

export function search (state = {}, action: any) {
  switch (action.type) {
    case searchConstants.SEARCH_ISSUES_SUCCESS:
      return {
        issues: action.searchResults
      }
    case searchConstants.SEARCH_ISSUES_FAILURE:
      return {
        error: action.error
      }
    default:
      return state
  }
}
