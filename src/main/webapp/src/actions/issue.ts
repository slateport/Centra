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
    await issue.getIssue(externalId)
      .then(response => dispatch(success(response)))
      .catch(e => {
        dispatch(error(e))
        dispatch(alertActions.error('Failed to fetch issue.'))
      })
  }

  function success (issueDto) { return { type: issueConstants.GET_SUCCESS, issueDto } }
  function error (response) { return { type: issueConstants.GET_SUCCESS, response } }
}

function getIssueComments (externalId: string) {
  return async dispatch => {
    await issue.getIssueComments(externalId)
      .then(data => dispatch(success(data)))
      .catch(e => {
        dispatch(error(e))
        dispatch(alertActions.error('Failed to fetch issue comments.'))
      })
  }

  function success (comments) { return { type: issueConstants.GET_COMMENTS_SUCCESS, comments } }
  function error (response) { return { type: issueConstants.GET_COMMENTS_FAILURE, response } }
}

function updateIssue (externalId, issueDto) {
  return async dispatch => {
    await issue.putIssue(externalId, issueDto)
      .then(data => {
        dispatch(success(data))
        dispatch(getIssue(externalId))
      })
      .catch(e => {
        dispatch(error(e))
        dispatch(alertActions.error('Failed to update issue.'))
      })
  }

  function success (comments) { return { type: issueConstants.PUT_SUCCESS, comments } }
  function error (response) { return { type: issueConstants.PUT_FAILURE, response } }
}

function addComment (externalId: string, comment:string) {
  return async dispatch => {
    await issue.addComment(externalId, comment)
      .then(response => {
        dispatch(success(response.data))
        dispatch(getIssueComments(externalId))
      })
      .catch(e => {
        dispatch(error(e))
        dispatch(alertActions.error('Failed to add comment'))
      })
  }

  function success (comments) { return { type: issueConstants.POST_SUCCESS, comments } }
  function error (response) { return { type: issueConstants.POST_FAILURE, response } }
}
