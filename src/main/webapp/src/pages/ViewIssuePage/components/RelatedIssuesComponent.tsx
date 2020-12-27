import {IconButton, Link, List, ListItem, ListItemIcon} from "@material-ui/core";
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import StatusChip from "./StatusChip";
import EditableIssueTypeField from "./EditableIssueTypeField";
import {isAuthenticated, issueHelper} from "../../../helpers";
import React from "react";
import {issue as issueService} from "../../../services";

interface IRelatedIssuedComponentProps {
    issue: any
}

class RelatedIssuesComponent extends React.Component<IRelatedIssuedComponentProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            links : []
        }

        this.deleteLink = this.deleteLink.bind(this)
        this.loadLinks = this.loadLinks.bind(this)
    }

    deleteLink(id) {
        return e => {
            issueService.deleteLink(id)
                .then(() => this.loadLinks())
        }
    }

    componentDidMount() {
        this.loadLinks()
    }

    loadLinks() {
        issueService.getLinks(issueHelper.buildExternalKey(this.props.issue))
            .then((links) => links.map(async (link) => {
                const currentIssue = issueHelper.buildExternalKey(this.props.issue)
                const fetchId =  (link.nodePublicId == currentIssue) ? link.linkPublicId : link.nodePublicId

                link.issue = await issueService.getIssue(fetchId)

                return link
            }))
            .then(linkPromises => Promise.all(linkPromises).then(links => this.setState({ links })))
    }

    render() {
        return (
            <List dense={true}>
                {this.state.links.map((link, index) => (
                    <ListItem key={index} disableGutters={true}>
                        <ListItemIcon>
                            <StatusChip label={link.issue.workflowState.label} isInitial={link.issue.workflowState.entry} isTerminus={link.issue.workflowState.isTerminus} />
                        </ListItemIcon>
                        <ListItemIcon>
                            <EditableIssueTypeField handleFn={() => {}} id={link.issue.issueTypeId} clickable={false} projectKey={link.issue.projectKey} preText={""} postText={""}/>
                        </ListItemIcon>
                        <Link href={"/browse/" + issueHelper.buildExternalKey(link.issue)}>{issueHelper.buildExternalKey(link.issue)}: {link.issue.title}</Link>
                        { isAuthenticated() &&
                        <IconButton edge={"end"} onClick={this.deleteLink(link.id)}>
                            <HighlightOffIcon/>
                        </IconButton>
                        }
                    </ListItem>
                ))}
            </List>

        )
    }
}

export default RelatedIssuesComponent