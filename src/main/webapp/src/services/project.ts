import { authHeader } from '../helpers'

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
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/${internalId}`, requestOptions)
}

function getAllProjects() {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/`, requestOptions)
}

function getIssueTypesForProject(internalId: string) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/${internalId}/issueTypes`, requestOptions)
}

function getPrioritiesForProject(projectKey: string) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/${projectKey}/priorities`, requestOptions)
}

function getPriorityById(internalId: string) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/priorities/${internalId}`, requestOptions)
}

function createNewProject(projectKey: string, projectName: string, description: string) {
    const body = {
        projectName, description, projectKey
    }

    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', ...authHeader()},
        body: JSON.stringify(body)
    }

    return fetch(`/api/projects`, requestOptions)
}

function deleteProject(internalId: string) {
    const requestOptions = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/${internalId}`, requestOptions)
}