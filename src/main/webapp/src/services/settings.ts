import httpClient from '../HttpClient'

export const settings = {
    getAllSettings
}

function getAllSettings() {
    return httpClient.get('/api/settings/')
        .then(response => response.data)
}