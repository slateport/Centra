import React from "react";
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import UserLite from "../domain/UserLite";
import { user as userService } from '../services'

interface IUserFieldPickerProps {
    userId?: string
    handleFn: Function
}

export default class UserPickerField extends React.Component<IUserFieldPickerProps, any> {
    constructor(props) {
        super(props);

        this.state = {
            users: []
        }
    }

    componentDidMount() {
        userService.getAllLite().then(users => this.setState({users}))
    }

    render() {
        return (
            <Autocomplete
                id="user-picker-field"
                options={this.state.users}
                getOptionLabel={(option: UserLite) => option.displayName}
                // value={this.state.users.find(u => (this.props.userId) ? u.id == this.props.userId : {}) || {}}
                getOptionSelected={(option, value) => option.id == value.id}
                renderInput={(params) => <TextField {...params} variant="outlined" />}
                onChange={(_, value: UserLite) => this.props.handleFn(value.id)}
            />
        )
    }
}