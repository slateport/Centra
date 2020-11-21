import React from "react";
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

interface IIssuePriorityEnumPickerFieldProps {
    selectedEnum: string
    handleFn: any
}

class IssuePriorityEnumPickerField extends React.Component<IIssuePriorityEnumPickerFieldProps, any> {


    render() {
        return (
            <Select
                labelId="demo-customized-select-label"
                id="demo-customized-select"
                onChange={this.props.handleFn}
                value={this.props.selectedEnum}
            >
                <MenuItem value={"HIGHEST"}>Highest</MenuItem>
                <MenuItem value={"HIGH"}>High</MenuItem>
                <MenuItem value={"MEDIUM"}>Medium</MenuItem>
                <MenuItem value={"LOW"}>Low</MenuItem>
                <MenuItem value={"LOWEST"}>Lowest</MenuItem>
            </Select>
        )
    }
}

export default IssuePriorityEnumPickerField