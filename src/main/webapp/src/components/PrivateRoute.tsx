import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'

function PrivateRoute ({ component: Component, ...rest }) {
  const isUserStored = localStorage.getItem('user')

  return (
        <Route {...rest} render={props => (
          (isUserStored) ? <Component {...props} />
            : <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
        )} />
  )
}

export { PrivateRoute }
