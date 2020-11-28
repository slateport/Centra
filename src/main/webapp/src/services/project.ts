import httpClient from '../HttpClient'

export const project = {
  getProject,
  getAllProjects,
  getIssueTypesForProject,
  getPrioritiesForProject,
  getPriorityById,
  createNewProject,
  deleteProject
}

function getProject (internalId: string) {
  return httpClient.get(`/api/projects/${internalId}`)
    .then(response => response.data)
}

function getAllProjects () {
  return httpClient.get('/api/projects/')
    .then(response => response.data)
}

function getIssueTypesForProject (internalId: string) {
  return httpClient.get(`/api/projects/${internalId}/issueTypes`)
    .then(response => response.data)
}

function getPrioritiesForProject (projectKey: string) {
  return httpClient.get(`/api/projects/${projectKey}/priorities`)
    .then(response => response.data)
}

function getPriorityById (internalId: string) {
  return httpClient.get(`/api/projects/priorities/${internalId}`)
    .then(response => response.data)
}

function createNewProject (projectKey: string, projectName: string, description: string) {
  const body = {
    projectName, description, projectKey
  }

  return httpClient.post('/api/projects', body)
    .then(response => response.data)
}

function deleteProject (internalId: string) {
  return httpClient.delete(`/api/projects/${internalId}`)
    .then(response => response.data)
}
