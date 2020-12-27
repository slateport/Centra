import React from 'react'
import Select from '@material-ui/core/Select'
import MenuItem from '@material-ui/core/MenuItem'
import { project } from '../services'
import {priorityMap} from "../pages/ViewIssuePage/components/EditablePriorityField";

interface IIssuePriorityEnumPickerFieldProps {
    projectKey: string
    selectedPriorityId: string
    handleFn: any
}

class IssuePriorityEnumPickerField extends React.Component<IIssuePriorityEnumPickerFieldProps, any> {
  constructor (props) {
    super(props)

    this.state = {
      priorityList: []
    }
  }

  componentDidMount () {
    project.getPrioritiesForProject(this.props.projectKey)
      .then(priorityList => this.setState({ priorityList }))
  }

  render () {
    return (
            <Select
                labelId="priority-picker"
                id="priority-picker"
                onChange={this.props.handleFn}
                defaultValue={this.props.selectedPriorityId}
            >

            {this.state.priorityList.map((priority) =>
                <MenuItem value={priority.id} key={priority.id}>{typeof priorityMap[(priority || {}).icon] === 'function' ? priorityMap[(priority || {}).icon](priority) : null}</MenuItem>
            )}
            </Select>
    )
  }
}

export default IssuePriorityEnumPickerField
