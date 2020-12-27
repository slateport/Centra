import React from 'react'
import styled from "styled-components";

export const TextArea = styled.textarea`
    margin: 18px 0 0 -8px;
    height: 44px;
    width: 100%;
    padding: 7px 7px 8px;
    border: none;
    resize: none;
    background: transparent;
    border: 1px solid transparent;
    box-shadow: 0 0 0 1px transparent;
    transition: background 0.1s;
    font-size:1.5rem;
    font-weight: 500;
    &:hover:not(:focus) {
      
    }
`;

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
            <TextArea
                ref={ref}
                {...rest}
            />
      )
    }
}
