import '../static/redactorx/redactorx.css'
import React from "react";
import RedactorX from '../static/redactorx/redactorx';
import { Button } from "@material-ui/core"
import {randomUuidString} from "../helpers/uuid";

class RedactorField extends React.Component<any, any> {
    private readonly id;
    private config = {subscribe: undefined};
    private redactor;

    constructor(props) {
        super(props);
        this.id = randomUuidString();
        this.saveFn = this.saveFn.bind(this);
        this.handleChange = this.handleChange.bind(this);

        this.state = {
            value: '',
        };
    }

    handleChange(e) {
        this.setState({ value: e });
    }

    componentDidMount() {
        const subscribe = {
            'editor.change': event => {
                var html = event.get('html');
                this.handleChange(html);
                return html
            }
        };

        if (typeof this.config.subscribe === 'undefined') {
            this.config.subscribe = subscribe
        } else {
            this.config.subscribe['editor.change'] = subscribe['editor.change'];
        }

        this.redactor = RedactorX('#'+this.id, this.config)
    }

    componentWillUnmount() {
        RedactorX('#'+this.id, 'destroy')
        this.redactor = null;
        document.getElementById("redactor-" + this.id).innerHTML = ''
    }

    saveFn (e) {
        this.props.saveFn(null, this.state.value)
    }

    render() {
        return (
            <div id={"redactor-" + this.id}>
                <textarea id={this.id} defaultValue={this.props.value} />
                <Button
                    component={Button}
                    fullWidth
                    variant="contained"
                    color="primary"
                    onClick={this.saveFn}
                >
                    Save
                </Button>
            </div>
        )
    }
}

export default RedactorField