import { installConstants } from '../constants'

export function install (state = {}, action: any) {
  switch (action.type) {
    case installConstants.INSTALL_SUCCESS:
      return {
        install: true
      }
    case installConstants.INSTALL_FAILURE:
      return {
        install: false
      }
    default:
      return state
  }
}
