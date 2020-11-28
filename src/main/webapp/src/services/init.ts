import httpClient from '../HttpClient'
import Init from '../domain/Init'

export const init = {
  getInit
}

function getInit (): Promise<any> {
  return httpClient.get<Init>('/api/init')
}
