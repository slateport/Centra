import { issueConstants } from '../constants'
import { alertActions } from './alert'
import { issue } from '../services'

export const issueActions = {
  getIssue,
  getIssueComments,
  updateIssue,
  addComment
}

function getIssue (externalId: string) {
  return async dispatch => {
    const response = await issue.getIssue(externalId)
    if (response.ok) {
      dispatch(success(await response.json()))
    } else {
      dispatch(error(response))
      dispatch(alertActions.error('Failed to fetch issue.'))
    }
  }

  function success (issueDto) { return { type: issueConstants.GET_SUCCESS, issueDto } }
  function error (response) { return { type: issueConstants.GET_SUCCESS, response } }
}

function getIssueComments (externalId: string) {
  return async dispatch => {
    const response = await issue.getIssueComments(externalId)
    if (response.ok) {
      dispatch(success(await response.json()))
    } else {
      dispatch(error(response))
      dispatch(alertActions.error('Failed to fetch issue comments.'))
    }
  }

  function success (comments) { return { type: issueConstants.GET_COMMENTS_SUCCESS, comments } }
  function error (response) { return { type: issueConstants.GET_COMMENTS_FAILURE, response } }
}

function updateIssue (externalId, issueDto) {
  return async dispatch => {
    const response = await issue.putIssue(externalId, issueDto)
    if (response.ok) {
      dispatch(success(await response.json()))
      dispatch(getIssue(externalId))
    } else {
      dispatch(error(response))
      dispatch(alertActions.error('Failed to update issue.'))
    }
  }

  function success (comments) { return { type: issueConstants.PUT_SUCCESS, comments } }
  function error (response) { return { type: issueConstants.PUT_FAILURE, response } }
}

function addComment (externalId: string, comment:string) {

  return async dispatch => {
    const response = await issue.addComment(externalId, comment);

    if (response.ok) {
        dispatch(success(await response.json()))
        dispatch(getIssueComments(externalId))
    } else {
      dispatch(error(response))
      dispatch(alertActions.error('Failed to add comment'))
    }

  }

  function success (comments) { return { type: issueConstants.POST_SUCCESS, comments } }
  function error (response) { return { type: issueConstants.POST_FAILURE, response } }
}