import httpClient from '../HttpClient'

export const boards = {
    createBoard,
    getAll,
    getBoardById
}

function createBoard(type: 'kanban' | 'scrum', projectIds: string[], name: string) {
    return httpClient.post(`/api/boards`, {
        type: type.toUpperCase(), projectIds, name
    })
}

function getAll() {
    return httpClient.get('/api/boards')
        .then(results => results.data)
}

function getBoardById(id: string) {
    return httpClient.get(`/api/boards/${id}`)
        .then(response => response.data)
}