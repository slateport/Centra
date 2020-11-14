import React, {Component} from "react";
import parse from 'html-react-parser';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import {issue as issueServices} from '../../../services'
import {RoundTimeAgo} from "../../../components/RoundTimeAgo";


interface IAuditHistoryProps {
    externalId: string
}

const normalisePropertyName = (propertyName) => {

    switch(propertyName){
        case 'workflowState.label':
            return "Status"

        default:
            return propertyName.charAt(0).toUpperCase() + propertyName.slice(1)
    }
}


const blacklistedChanges = [
    'workflowState.isEntry',
    'workflowState.isTerminus',
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
        return (
            <React.Fragment>
                <TableContainer>
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
                            .filter((change) => blacklistedChanges.indexOf(change.propertNameWithPath) == -1)
                            .map((change) => (
                            <TableRow key={change.affectedGlobalId.cdoId}>
                                <TableCell>{change.commitMetadata.author} <RoundTimeAgo date={new Date(change.commitMetadata.commitDate)} /></TableCell>
                                <TableCell>{normalisePropertyName(change.propertyNameWithPath)}</TableCell>
                                <TableCell>{parse(typeof change.left == 'string' ? change.left: '')}</TableCell>
                                <TableCell>{parse(typeof change.right == 'string' ? change.right: '')}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </TableContainer>
            </React.Fragment>
        )
    }
}