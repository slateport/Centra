import httpClient from '../HttpClient'

export const workflow = {
  getWorkflow,
  listWorkflows,
  createWorkflow,
  updateWorkflow
}

function getWorkflow (workflowId) {
  return httpClient.get(`api/workflow/${workflowId}`)
    .then(response => response.data)
}

function listWorkflows () {
  return httpClient.get(`api/workflow`)
      .then(response => response.data)
}

function createWorkflow(workflowDto) {
  return httpClient.post(`api/workflow`, workflowDto)
      .then(response => response.data)
}

function updateWorkflow(workflowId, workflowDto) {
  return httpClient.put(`api/workflow/${workflowId}`, workflowDto )
    .then(response  => response.data)
}
