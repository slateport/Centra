import React, { Component } from 'react'
import parse from 'html-react-parser';
import {isAuthenticated} from "../helpers";

export default class EditableContainer extends React.Component<any, any> {
    private count = 0;
    private timeout = 250;

    constructor (props) {
        super(props)

        this.count = 0

        this.state = {
            edit: false,
        }
    }

    componentWillUnmount () {
        if (this.timeout) clearTimeout(this.timeout)
    }

    handleClick (e) {
        if (this.timeout) clearTimeout(this.timeout)
        this.count++

        this.timeout = setTimeout(() => {
            if (this.count === 2) {
                this.setState({
                    edit: true,
                    children: this.props.children
                })
            }

            // reset count
            this.count = 0
        }, 250)
    }

    handleBlur () {
        const { handlefn } = this.props;
        handlefn(this.state);
        this.setState({
            edit: false
        })
    }

    handleOnChange = (event, value, callback) => {
        this.setState({
            children: value || event.target?.value
        },callback)

    }

    onKeyPress = (e) => {
        e.persist();
        if (e.key == 'Enter'){
            this.handleBlur()
        }
    }

    saveAll = (event, value) => {
        this.handleOnChange(event, value, () => this.handleBlur())
    }

    render () {
        const {children, Component, ...rest} = this.props
        const {edit} = this.state

        if (edit && isAuthenticated()) {
            return (
                <Component
                    autoFocus
                    onBlur={this.handleBlur.bind(this)}
                    value={this.state.children}
                    onChange={this.handleOnChange}
                    onKeyPress={this.onKeyPress}
                    saveFn={this.saveAll}
                />
            )
        } else {
            return (
                <span
                    onClick={this.handleClick.bind(this)}
                    {...rest}
                >
          {parse(children as string)}
        </span>
            )
        }
    }
}