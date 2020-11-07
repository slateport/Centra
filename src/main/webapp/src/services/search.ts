export const search = {
    searchIssues
}

function searchIssues (cql: string) {
    return fetch('/api/search?cql='+cql)
}
