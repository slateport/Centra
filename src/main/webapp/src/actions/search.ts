import { searchConstants } from '../constants'
import {search} from "../services";
import {alertActions} from "./alert";

export const searchActions = {
    searchIssue
}

function searchIssue (cql: string) {
    return async dispatch => {
        const response = await search.searchIssues(cql)
        if (response.ok) {
            dispatch(success(await response.json()))
        } else {
            dispatch(error(response))
            dispatch(alertActions.error("Search Failed"))
        }
    }

    function success (searchResults) { return { type: searchConstants.SEARCH_ISSUES_SUCCESS, searchResults } }
    function error (response) { return { type: searchConstants.SEARCH_ISSUES_FAILURE, response } }
}
