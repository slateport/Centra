import httpClient from '../HttpClient'

export const settings = {
    getAllSettings,
    saveSetting
}

function getAllSettings() {
    return httpClient.get('/api/settings/')
        .then(response => response.data)
}

function saveSetting(key, value) {
    return httpClient.put(`/api/settings/${key}`, {value})
}