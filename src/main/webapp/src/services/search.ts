import httpClient from "../HttpClient";

export const search = {
    searchIssues
}

function searchIssues (cql: string) {
    return httpClient.get('/api/search?cql=' + cql)
        .then(response => response.data)
}
