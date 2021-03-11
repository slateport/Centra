import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import { store } from './helpers';
import { App } from './App';
import draw2d from "draw2d";

render(
    <Provider store={store as any}>
        <App />
    </Provider>,
    document.getElementById('app')
);