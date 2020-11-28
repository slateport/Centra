import axios from 'axios';
import {authHeader} from "../helpers";

const httpClient = axios.create();

httpClient.interceptors.request.use((config) => {
    config.headers.Authorization = authHeader().Authorization
    config.headers['Content-Type'] = 'application/json'
    return config
})

httpClient.interceptors.response.use(response => {
    return response
}, error => {
    if (error.response.status === 401) {
        alert("Your session has expired or you are not currently logged in")
    }
    throw error
})

export default httpClient;