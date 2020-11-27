import { searchConstants } from '../constants'
import {search} from "../services";
import {alertActions} from "./alert";

export const searchActions = {
    searchIssue
}

function searchIssue (cql: string) {
    return async dispatch => {
        await search.searchIssues(cql)
            .then(response => {
                dispatch(success(response))
            })
            .catch(e => {
                dispatch(error(e))
                dispatch(alertActions.error("Search Failed"))
            })
    }

    function success (searchResults) { return { type: searchConstants.SEARCH_ISSUES_SUCCESS, searchResults } }
    function error (response) { return { type: searchConstants.SEARCH_ISSUES_FAILURE, response } }
}
