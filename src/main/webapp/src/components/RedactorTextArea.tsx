import '../static/redactorx/redactorx.css'
import React from "react";
import RedactorX from "../static/redactorx/redactorx";

interface IRedactorTextArea {
    id: string,
    handleChange: Function,
    name: string
}

export default class RedactorTextArea extends React.Component<IRedactorTextArea, any> {
    private redactor: any;
    private config = {subscribe: undefined};

    componentDidMount() {
        const subscribe = {
            'editor.change': event => {
                var html = event.get('html');
                this.props.handleChange({ target: {name: 'description', value: html}});
                return html
            }
        };

        if (typeof this.config.subscribe === 'undefined') {
            this.config.subscribe = subscribe
        } else {
            this.config.subscribe['editor.change'] = subscribe['editor.change'];
        }

        this.redactor = RedactorX('#'+this.props.id, this.config)
    }

    render() {
        return (
            <textarea
                id={this.props.id}
                name={this.props.name}
            />
        )
    }
}