import React from 'React'
import {issue as issueService} from "../../../services";
import {Badge as MuiBadge} from "@material-ui/core";
import styled from "styled-components";

const Badge = styled(MuiBadge)`
    right: -10px;
`

const WatchingText = styled.span`
    margin-left: 25px;
    color: #1976d2;
    
    &:hover {
        text-decoration:underline;
    }
`

interface IWatchComponent {
    externalId: string,
    currentUser: any,
}

interface IAmIWatchingComponent {
    watchers: any[],
    currentUser: any,
    startWatching: Function,
    stopWatching: Function
}

class WatchComponent extends React.Component<IWatchComponent, any> {

    constructor(props) {
        super(props);

        this.state = {
            watchers: undefined
        }

        this.startWatching = this.startWatching.bind(this)
        this.stopWatching  = this.stopWatching.bind(this)
    }

    stopWatching() {
        issueService.removeWatcher(this.props.externalId, this.props.currentUser.username)
            .then(watchers => this.setState({ watchers }))
    }

    startWatching() {
        this.setState({ watchers: this.state.watchers.push(this.props.currentUser)})
        issueService.addWatcher(this.props.externalId, this.props.currentUser.username)
            .then(watchers => this.setState({ watchers }))
    }


    componentDidMount() {
        issueService.getWatchers(this.props.externalId)
            .then(watchers => this.setState({ watchers }))
    }

    render() {
        if (this.state.watchers === undefined) {
            return "Fetching watchers..."
        }

        if (this.state.watchers.length >= 0) {
            return <React.Fragment>
                <Badge showZero={true} color="secondary" badgeContent={this.state.watchers.length} />
                <AmIWatchingComponent watchers={this.state.watchers} currentUser={this.props.currentUser}
                    startWatching={this.startWatching} stopWatching={this.stopWatching}
                />
            </React.Fragment>
        }

        return "Loading watchers..."
    }
}

class AmIWatchingComponent extends React.Component<IAmIWatchingComponent, any> {

    constructor(props) {
        super(props);
    }


    render() {
        const containsCurrentUser =
            this.props.watchers.filter(watcher => watcher.id === this.props.currentUser.id).length > 0

        if (containsCurrentUser) {
            return <WatchingText onClick={() => this.props.stopWatching()}>Stop Watching</WatchingText>
        } else {
            return <WatchingText onClick={() => this.props.startWatching()}>Start Watching</WatchingText>
        }
    }

}

export { WatchComponent }
