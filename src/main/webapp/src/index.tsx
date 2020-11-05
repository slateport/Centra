import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import { store } from './helpers';
import { App } from './App';


render(
    <Provider store={store as any}>
        <App />
    </Provider>,
    document.getElementById('app')
);