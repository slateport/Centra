import httpClient from '../HttpClient'

export const workflow = {
  getWorkflow,
  listWorkflows,
  createWorkflow,
}

function getWorkflow (workflowId) {
  return httpClient.get(`api/workflow/${workflowId}`)
    .then(response => response.data)
}

function listWorkflows (workflowId) {
  return httpClient.get(`api/workflow`)
      .then(response => response.data)
}

function createWorkflow(workflowDto) {
  return httpClient.post(`api/workflow`, workflowDto)
      .then(response => response.data)
}
