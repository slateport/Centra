import React from 'react'
import { isAuthenticated } from '../helpers'
import {MenuItem, NativeSelect} from "@material-ui/core";
import NativeSelectInput from "@material-ui/core/NativeSelect/NativeSelectInput";

export default class StandardSelectField extends React.Component<any, any> {
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
            <NativeSelect
                ref={ref}
                disabled={!isAuthenticated()}
                {...rest}
                variant="outlined"
            >
                {this.props.options.map(option =>
                    <option key={option}
                              value={option}
                              selected={this.props.value === option}
                    >{option}</option>
                )}
            </NativeSelect>
      )
    }
}
