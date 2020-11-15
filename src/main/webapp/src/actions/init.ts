import { initConstants } from '../constants'
import {authHeader} from "../helpers";

export const initActions = {
  loadInit
}

function loadInit () {
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', ...authHeader() }
  }

  return async dispatch => {
    const response = await fetch('/api/init', requestOptions)
    if (response.ok) {
      dispatch(success(await response.json()))
    } else {
      dispatch(error(response))
    }
  }

  function success (initDto) { return { type: initConstants.INIT_SUCCESS, initDto } }
  function error (response) { return { type: initConstants.INIT_FAILURE, response } }
}
