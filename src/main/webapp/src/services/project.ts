import { authHeader } from '../helpers'

export const project = {
    getProject,
    getAllProjects,
    getIssueTypesForProject,
    getPrioritiesForProject,
    getPriorityById
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