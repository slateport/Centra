import httpClient from "../HttpClient";

export const install = {
  completeInstallation
}

function completeInstallation (username: string, password: string, licenseKey: string) {
  return httpClient.post('/api/install', { username, password, licenseKey })
}
