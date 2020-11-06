import { authHeader } from '../helpers'

export const user = {
  login,
  logout,
  getAll,
  getUser
}

function login (username: string, password: string) {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password })
  }

  return fetch('/api/login', requestOptions)
    .then(handleResponse)
    .then(user => {
      // store user details and jwt token in local storage to keep user logged in between page refreshes
      localStorage.setItem('user', user )

      return user
    })
}

function logout () {
  // remove user from local storage to log user out
  localStorage.removeItem('user')
}

function getAll () {
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  }

  return fetch('/api/users', requestOptions).then(handleResponse)
}

function getUser(userId: string) {
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  }

  return fetch(`/api/users/${userId}`, requestOptions).then(
      response => {
        if (response.ok){
          return Promise.resolve(response.json());
        } else {
          return Promise.reject(response.json());
        }
      }
  )
}

function handleResponse (response: Response) {
  return response.text().then(text => {
    const data = text && JSON.parse(text)
    if (!response.ok) {
      if (response.status === 401) {
        // auto logout if 401 response returned from api
        logout()
        location.reload(true)
      }

      const error = (data && data.message) || response.statusText
      return Promise.reject(error)
    }

    return response.headers.get('Authorization').replace('Bearer ', '')
  })
}
