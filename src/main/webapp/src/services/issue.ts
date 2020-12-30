import httpClient from '../HttpClient'

export const issue = {
  getIssue,
  getIssueComments,
  putIssue,
  getWorkflowTransitions,
  postWorkflowTransitions,
  addComment,
  getAllLabels,
  createIssue,
  getAuditChanges,
  getIssueTypeById,
  getLinks,
  deleteLink,
  createLink,
  getCustomFieldValues
}

function getIssue (externalId: string) {
  return httpClient.get(`/api/issues/${externalId}`)
    .then(response => response.data)
}

function getIssueComments (externalId: string) {
  return httpClient.get(`/api/issues/${externalId}/comments`)
    .then(response => response.data)
}

function putIssue (externalId: string, issueDto) {
  return httpClient.put(`/api/issues/${externalId}`, issueDto)
}

function
getWorkflowTransitions (externalId: string) {
  return httpClient.get(`/api/issues/${externalId}/transitions`)
    .then(response => response.data)
}

function postWorkflowTransitions (externalId: string, transitionDto) {
  return httpClient.post(`/api/issues/${externalId}/transitions`, transitionDto)
}

function addComment (externalId: string, comment:string) {
  return httpClient.post(`/api/issues/${externalId}/comments`, { text: comment })
}

function getAllLabels () {
  return httpClient.get('/api/issues/labels?labelValue=')
}

function createIssue (title, description, projectKey, labels, assigneeId, issueTypeId, issuePriorityId) {
  const body = {
    title, description, projectKey, labels, assigneeId, issueTypeId, issuePriorityId
  }

  return httpClient.post('/api/issues/', body)
}

function getAuditChanges (externalId: string) {
  return httpClient.get(`/api/issues/${externalId}/changes`)
    .then(response => response.data)
}

function getIssueTypeById (id: string) {
  return httpClient.get(`/api/issues/types/${id}`)
    .then(response => response.data)
}

function getLinks (externalId: string) {
  return httpClient.get(`/api/issues/${externalId}/links`)
    .then(response => response.data)
}

function deleteLink (id: string) {
  return httpClient.delete(`/api/issues/links/${id}`)
}

function createLink (nodePublicId, linkPublicId) {
  return httpClient.post('/api/issues/links', { nodePublicId, linkPublicId })
}

function getCustomFieldValues(externalId: string) {
  return httpClient.get(`/api/customfields/issue/${externalId}`)
      .then(response => response.data)
}
