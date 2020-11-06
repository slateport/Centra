import { authHeader } from '../helpers'

export const issue = {
  getIssue,
  getIssueComments
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
