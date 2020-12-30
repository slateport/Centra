import React from "react";
import { issue } from '../../../services'
import { issueHelper } from '../../../helpers'
import {Grid} from "@material-ui/core";

interface ICustomFieldsComponentProps {
    issue: any
}

export default class CustomFieldsComponent extends React.Component<ICustomFieldsComponentProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            customFields : []
        }
    }

    componentDidMount() {
        issue.getCustomFieldValues(issueHelper.buildExternalKey(this.props.issue))
            .then(customFields => this.setState({ customFields }))
    }

    render() {
        console.log(this.state.customFields)
        return (
            this.state.customFields.map(fieldDto =>
                <React.Fragment>
                    <Grid item xs={4}>{fieldDto.customField.name}</Grid>
                    <Grid item xs={8}>{fieldDto.customFieldValue.stringValue}</Grid>
                </React.Fragment>
            )
        )
    }
}