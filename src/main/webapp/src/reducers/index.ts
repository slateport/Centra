import { combineReducers } from 'redux'
import { authentication } from './authentication'
import { users } from './users'
import { alert } from './alert'
import { init } from './init'
import { install } from './install'
import { issue } from './issue'
import { project } from './project'
import { search } from './search'

const rootReducer = combineReducers({
  authentication,
  users,
  alert,
  init,
  install,
  issue,
  project,
  search
})

export default rootReducer
