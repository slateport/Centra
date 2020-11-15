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


interface IAuditHistoryProps {
    externalId: string
}

const normalisePropertyName = (propertyName) => {

    switch(propertyName){
        case 'workflowState:label':
            return "Status"

        case 'assigneeId':
            return "Assignee"

        default:
            return propertyName.charAt(0).toUpperCase() + propertyName.slice(1)
    }
}

const blacklistedChanges = [
    'workflowState:isEntry',
    'workflowState:isTerminus',
]


export default class AuditHistory extends Component<IAuditHistoryProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            changes : []
        }
    }

    componentDidMount() {
        issueServices.getAuditChanges(this.props.externalId)
            .then(
                response => response.json()
                    .then(changes => this.setState({changes}))

            )
    }

    render() {
        console.log(blacklistedChanges)
        return (
            <React.Fragment>
                <TableContainer>
                    <Table>
                        <TableHead>
                            <TableRow>
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
                                        <TableCell><EditablePeopleField userId={change.changeByUserId} handleFn={() => {}} clickable={false}/> <RoundTimeAgo date={new Date(change.changeDate)} /></TableCell>
                                        <TableCell>{normalisePropertyName(change.propertyNameWithPath)}</TableCell>
                                        <TableCell>{parse(typeof change.left == 'string' ? change.left: '')}</TableCell>
                                        <TableCell>{parse(typeof change.right == 'string' ? change.right: '')}</TableCell>
                                    </TableRow>
                                ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </React.Fragment>
        )
    }
}