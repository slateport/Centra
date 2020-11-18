import { authHeader } from '../helpers'

export const project = {
    getProject,
    getAllProjects,
    getIssueTypesForProject
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

function getIssueTypeById(internalId: string) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/issueTypes/${internalId}`, requestOptions)
}