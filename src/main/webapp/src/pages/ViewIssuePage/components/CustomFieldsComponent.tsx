import React from "react";
import { issue } from '../../../services'
import { issueHelper } from '../../../helpers'
import {Grid} from "@material-ui/core";
import EditableContainer from "../../../components/EditableContainer";
import StandardTextField from "../../../components/StandardTextField";
import {alertActions} from "../../../actions";

interface ICustomFieldsComponentProps {
    issue: any
    dispatch: Function
}

export default class CustomFieldsComponent extends React.Component<ICustomFieldsComponentProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            customFields : []
        }

        this.onSaveCustomFieldValue = this.onSaveCustomFieldValue.bind(this)
    }

    componentDidMount() {
        issue.getCustomFieldValues(issueHelper.buildExternalKey(this.props.issue))
            .then(customFields => this.setState({ customFields }))
    }

    onSaveCustomFieldValue(customFieldValue) {
        return state => {
            customFieldValue.stringValue = state.children
            issue.setCustomFieldValue(customFieldValue, issueHelper.buildExternalKey(this.props.issue))
                .catch(e => this.props.dispatch(alertActions.error("Failed to update custom field")))
            this.forceUpdate()
        }
    }

    render() {
        return (
            this.state.customFields.map(fieldDto =>
                <React.Fragment>
                    <Grid item xs={4}>{fieldDto.customField.name}</Grid>
                    <Grid item xs={8}>
                        <EditableContainer Component={StandardTextField}
                                           handlefn={this.onSaveCustomFieldValue(fieldDto.customFieldValue)}
                        >
                            {fieldDto.customFieldValue.stringValue}
                        </EditableContainer>
                    </Grid>
                </React.Fragment>
            )
        )
    }
}