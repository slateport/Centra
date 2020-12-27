import React, {Component} from "react";
import parse from 'html-react-parser';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import {issue as issueServices} from '../../../services'
import {RoundTimeAgo} from "../../../components/RoundTimeAgo";
import EditablePeopleField from "./EditablePeopleField";
import EditableIssueTypeField from "./EditableIssueTypeField";
import EditablePriorityField from "./EditablePriorityField";
import {alertActions} from "../../../actions";


interface IAuditHistoryProps {
    externalId: string
}

const normalisePropertyName = (propertyName) => {

    switch(propertyName){
        case 'workflowState:label':
            return "Status"

        case 'assigneeId':
            return "Assignee"

        case 'issueTypeId':
            return "Issue type"

        case 'issuePriorityId':
            return "Priority"

        default:
            return propertyName.charAt(0).toUpperCase() + propertyName.slice(1)
    }
}

const blacklistedChanges = [
    'workflowState:isEntry',
    'workflowState:isTerminus',
]

const renderRight = (change) => {
    switch(change.propertyNameWithPath) {
        case 'title':
            return parse(change.right || "")

        case 'description':
            return parse(change.right || "")

        case 'workflowState:label':
            return change.right;

        case 'assigneeId':
            return (
                <EditablePeopleField userId={change.right} handleFn={() => {}} clickable={false}/>
            )

        case 'issueTypeId':
            return (
                <EditableIssueTypeField id={change.right} handleFn={() => {}} clickable={false} projectKey={null} preText={""} postText={""}/>
            )

        case 'issuePriorityId':
            return (
                <EditablePriorityField priorityId={change.right} clickable={false} handleFn={() => {}} projectKey={null} preText={""} postText={""}/>
            )

        default:
            return change.right;
    }
}

const renderLeft = (change) => {
    switch(change.propertyNameWithPath) {
        case 'title':
            return parse(change.left || "")

        case 'description':
            return parse(change.left || "")

        case 'workflowState:label':
            return change.left;

        case 'assigneeId':
            return (
                <EditablePeopleField userId={change.left} handleFn={() => {}} clickable={false}/>
            )

        case 'issueTypeId':
            return (
                <EditableIssueTypeField id={change.left} handleFn={() => {}} clickable={false} projectKey={null} preText={""} postText={""}/>
            )

        case 'issuePriorityId':
            return (
                <EditablePriorityField priorityId={change.left} clickable={false} handleFn={() => {}} projectKey={null} preText={""} postText={""}/>
            )

        default:
            return change.left;
    }
}


export default class AuditHistory extends Component<IAuditHistoryProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            changes : []
        }
    }

    componentDidMount() {
        issueServices.getAuditChanges(this.props.externalId)
            .then(changes => {
                console.log(changes)
                return changes
            })
            .then(changes => this.setState({ changes }))
            .catch(e => alertActions.error("Failed to fetch history."))
    }

    render() {
        return (
            <React.Fragment>
                <TableContainer>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Who</TableCell>
                                <TableCell>When</TableCell>
                                <TableCell>Field</TableCell>
                                <TableCell>Original Value</TableCell>
                                <TableCell>New Value</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.state.changes
                                .filter((change) => blacklistedChanges.indexOf(change.propertyNameWithPath) == -1)
                                .map((change, index) => (
                                    <TableRow key={index}>
                                        <TableCell component="th" scope="row"><EditablePeopleField userId={change.changeByUserId} handleFn={() => {}} clickable={false}/></TableCell>
                                        <TableCell><RoundTimeAgo date={new Date(change.changeDate)} /></TableCell>
                                        <TableCell>{normalisePropertyName(change.propertyNameWithPath)}</TableCell>
                                        <TableCell>{renderLeft(change)}</TableCell>
                                        <TableCell>{renderRight(change)}</TableCell>
                                    </TableRow>
                                ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </React.Fragment>
        )
    }
}