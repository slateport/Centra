import React from 'react'
import styled from 'styled-components'
import { isAuthenticated } from '../helpers'
import {TextField} from "@material-ui/core";

export default class StandardTextField extends React.Component<any, any> {
    private ref;

    constructor (props) {
      super(props)
      this.ref = React.createRef()
    }

    componentDidMount () {
      this.ref && this.ref.focus()
    }

    render () {
      const { autoFocus, ...rest } = this.props

      // auto focus
      const ref = autoFocus ? (ref) => { this.ref = ref } : null
      return (
            <TextField
                ref={ref}
                disabled={!isAuthenticated()}
                {...rest}
            />
      )
    }
}
