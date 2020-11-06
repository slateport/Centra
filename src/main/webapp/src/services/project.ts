import { authHeader } from '../helpers'

export const project = {
    getProject
}

function getProject (internalId: string) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', ...authHeader() }
    }

    return fetch(`/api/projects/${internalId}`, requestOptions)
}
