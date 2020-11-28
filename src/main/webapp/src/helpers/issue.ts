export const issueHelper = {
  buildExternalKey
}

function buildExternalKey (issue) {
  return issue.projectKey + '-' + issue.externalId
}
