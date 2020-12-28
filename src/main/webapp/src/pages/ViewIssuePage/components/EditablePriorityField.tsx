import React, {useEffect, useState} from "react";
import styled from "styled-components";
import * as Icon from "react-feather";
import {Button, Chip} from "@material-ui/core";
import {blueGrey, red, yellow} from "@material-ui/core/colors";
import IssuePriorityEnumPickerField from "../../../components/IssuePriorityEnumPickerField";
import {project} from "../../../services";

const priorityPromise = (id) => project.getPriorityById(id)

const BlueChip = styled(Chip)`
  background-color: ${blueGrey[700]};
  border-radius: 5px;
  color: ${props => props.theme.palette.common.white};
  font-size: 60%;
  height: 25px;
  margin-left: 2px;
  margin-bottom: 1px;
  padding: 4px 0;
  font-weight: bold;
  text-transform: uppercase;

  span {
    padding-left: ${props => props.theme.spacing(1.5)}px;
    padding-right: ${props => props.theme.spacing(1.5)}px;
  }
`;

const YellowChip = styled(Chip)`
  background-color: ${yellow[800]};
  border-radius: 5px;
  color: ${props => props.theme.palette.common.white};
  font-size: 60%;
  height: 25px;
  margin-left: 2px;
  margin-bottom: 1px;
  padding: 4px 0;
  font-weight: bold;
  text-transform: uppercase;

  span {
    padding-left: ${props => props.theme.spacing(1.5)}px;
    padding-right: ${props => props.theme.spacing(1.5)}px;
  }
`;

const RedChip = styled(Chip)`
  background-color: ${red[700]};
  border-radius: 5px;
  color: ${props => props.theme.palette.common.white};
  font-size: 60%;
  height: 25px;
  margin-left: 2px;
  margin-bottom: 1px;
  padding: 4px 0;
  font-weight: bold;
  text-transform: uppercase;

  span {
    padding-left: ${props => props.theme.spacing(1.5)}px;
    padding-right: ${props => props.theme.spacing(1.5)}px;
  }
`;

const priorityMap = {
    "ChevronsDown": (priority) => <BlueChip icon={<Icon.ChevronsDown color={"white"} size={16}/>} label={priority.label} />,
    "ChevronDown": (priority) =><BlueChip icon={<Icon.ChevronDown color={"white"} size={16} />} label={priority.label} />,
    "Code": (priority) => <YellowChip icon={<Icon.Code  color={"white"} size={16}/>} label={priority.label} />,
    "ChevronUp": (priority) => <RedChip icon={<Icon.ChevronUp color={"white"} size={16} />} label={priority.label} />,
    "ChevronsUp": (priority) => <RedChip icon={<Icon.ChevronsUp color={"white"} size={16} />} label={priority.label} />,
    undefined: <RedChip icon={<Icon.ChevronsUp color={"white"} size={16} />} label={"Unknown"} />
}

const EditablePriorityField = ({priorityId, handleFn, clickable, projectKey, preText, postText}) => {

    if (!priorityId) {
        return (<span>Unknown</span>)
    }

    let count = 0;
    let timeout = 250;

    const handleClick = (e) => {
        if (timeout) clearTimeout(timeout)
        count++

        timeout = setTimeout(() => {
            if (count === 1) {
                setEdit(true)
            }

            // reset count
            count = 0
        }, 250)
    }

    const [edit, setEdit] = useState(false);
    const [priority, setPriority] = useState(null);

    useEffect(() => {
        priorityPromise(priorityId).then(data => setPriority(data))
    }, []);
    const wrappedHandleFn = (val) => {
        setEdit(false)
        handleFn(val)

    }

    if (edit) {
        return (
            <React.Fragment>
                {preText} <IssuePriorityEnumPickerField selectedPriorityId={priorityId} handleFn={(val) => wrappedHandleFn(val)} projectKey={projectKey}/>
            </React.Fragment>
        )
    } else {
        const fn = priorityMap[(priority || {}).icon]
        return (
            <Button color="primary" onClick={clickable ? handleClick : (e) => {}}>{preText} {typeof fn === 'function' ? fn(priority) : null} {postText}</Button>
        )
    }
}

export default EditablePriorityField
export { priorityMap }
