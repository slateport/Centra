export const workflow = {
    getWorkflow
}

function getWorkflow (workflowId) {
    return fetch(`api/workflow/${workflowId}`)
        .then(data => data.json())
}
