import { userConstants } from '../constants'
import { user as userService } from '../services'
import { alertActions } from './'
import { history } from '../helpers'

export const userActions = {
  login,
  logout
}

function login (username, password) {
  return dispatch => {
    dispatch(request({ username }))

    userService.login(username, password)
      .then(
        user => {
            dispatch(success(user))
            history.push('/')
            location.reload()
        },
        error => {
          dispatch(alertActions.error('Login failed!'))
          dispatch(failure(error))
          console.log(error)
        }
      )
  }

  function request (user) { return { type: userConstants.LOGIN_REQUEST, user } }
  function success (user) { return { type: userConstants.LOGIN_SUCCESS, user } }
  function failure (error) { return { type: userConstants.LOGIN_FAILURE, error } }
}

function logout () {
  userService.logout()
  return { type: userConstants.LOGOUT }
}
