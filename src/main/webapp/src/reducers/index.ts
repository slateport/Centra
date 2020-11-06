import { combineReducers } from 'redux';
import { authentication } from './authentication';
import { users } from './users';
import { alert } from './alert';
import { init } from './init';
import { install } from './install';

const rootReducer = combineReducers({
  authentication,
  users,
  alert,
  init,
  install,
});

export default rootReducer;