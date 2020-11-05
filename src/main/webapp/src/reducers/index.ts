import { combineReducers } from 'redux';

import { authentication } from './authentication';
import { users } from './users';
import { alert } from './alert';
import { init } from './init';

const rootReducer = combineReducers({
  authentication,
  users,
  alert,
  init,
});

export default rootReducer;