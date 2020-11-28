import UserLite from '../domain/UserLite'
import httpClient from '../HttpClient'

export const user = {
  login,
  logout,
  getUser,
  getAllLite,
  registerUser
}

function login (username: string, password: string) {
  return httpClient.post('/api/login', { username, password })
    .then((response) => {
      const token = response.headers.authorization.replace('Bearer ', '')
      localStorage.setItem('user', token)
    })
    .then(() => location.href = '/')
}

function logout () {
  localStorage.removeItem('user')
}

function getUser (userId: string) {
  return httpClient.get(`/api/users/${userId}`)
    .then(response => response.data)
}

function getAllLite () : Promise<UserLite[]> {
  return httpClient.get<UserLite[]>('/api/users/lite')
    .then(response => response.data)
}

function registerUser (username: string, password: string, displayName: string, emailAddress: string) {
  return httpClient.post('/api/users', { username, password, displayName, emailAddress })
    .then(response => response.data)
}
