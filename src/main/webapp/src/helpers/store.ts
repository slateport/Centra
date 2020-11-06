import { routerMiddleware } from 'react-router-redux'
import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import { createLogger } from 'redux-logger';
import rootReducer from '../reducers';
import { history } from "./history";

const loggerMiddleware = createLogger();
export const store = createStore(
    rootReducer,
    applyMiddleware(
        routerMiddleware(history),
        thunkMiddleware,
        //loggerMiddleware
    )
);