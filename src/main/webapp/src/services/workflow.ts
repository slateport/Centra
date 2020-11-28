import httpClient from '../HttpClient'

export const workflow = {
  getWorkflow
}

function getWorkflow (workflowId) {
  return httpClient.get(`api/workflow/${workflowId}`)
    .then(response => response.data)
}
