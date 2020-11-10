import { authHeader } from '../helpers'

export const issue = {
  getIssue,
  getIssueComments,
  putIssue,
  getWorkflowTransitions,
  postWorkflowTransitions
}

function getIssue (externalId: string) {
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', ...authHeader() }
  }

  return fetch(`/api/issues/${externalId}`, requestOptions)
}

function getIssueComments (externalId: string) {
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', ...authHeader() }
  }

  return fetch(`/api/issues/${externalId}/comments`, requestOptions)
}

function putIssue(externalId: string, issueDto) {
  const requestOptions = {
    method: 'PUT',
    headers: {'Content-Type': 'application/json', ...authHeader()},
    body: JSON.stringify(issueDto)
  }

    return fetch(`/api/issues/${externalId}`, requestOptions)
}

function getWorkflowTransitions(externalId: string) {
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', ...authHeader() }
  }

  return fetch(`/api/issues/${externalId}/transitions`, requestOptions)
}

function postWorkflowTransitions(externalId: string, transitionDto) {
  const requestOptions = {
    method: 'POST',
    headers: {'Content-Type': 'application/json', ...authHeader()},
    body: JSON.stringify(transitionDto)
  }


  return fetch(`/api/issues/${externalId}/transitions`, requestOptions)
}