import axios from 'axios'
import { authHeader, isAuthenticated } from '../helpers'
import { user as userService } from '../services'

const httpClient = axios.create()

httpClient.interceptors.request.use((config) => {
  config.headers.Authorization = authHeader().Authorization
  config.headers['Content-Type'] = 'application/json'
  return config
})

httpClient.interceptors.response.use(response => {
  return response
}, error => {
  if (error.response.status === 401 && isAuthenticated()) {
    alert('Your session has expired or you are not currently logged in')
    userService.logout();
    location.reload();
  }
  throw error
})

export default httpClient
