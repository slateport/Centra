import {authHeader} from "../helpers";

export const init = {
  getInit
}

function getInit () {
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', ...authHeader() }
  }

  return fetch('/api/init', requestOptions)
    .then(initData => initData.json())
}
