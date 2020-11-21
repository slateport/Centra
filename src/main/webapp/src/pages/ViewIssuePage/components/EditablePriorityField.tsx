import React, {useState} from "react";
import styled from "styled-components";
import * as Icon from "react-feather";
import {Button, Chip} from "@material-ui/core";
import {blueGrey, red, yellow} from "@material-ui/core/colors";
import IssuePriorityEnumPickerField from "../../../components/IssuePriorityEnumPickerField";

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
    "LOWEST": <BlueChip icon={<Icon.ChevronsDown color={"white"} size={16}/>} label={"Lowest"} />,
    "LOW": <BlueChip icon={<Icon.ChevronDown color={"white"} size={16} />} label={"Low"} />,
    "MEDIUM": <YellowChip icon={<Icon.Code  color={"white"} size={16}/>} label={"Medium"} />,
    "HIGH": <RedChip icon={<Icon.ChevronUp color={"white"} size={16} />} label={"High"} />,
    "HIGHEST": <RedChip icon={<Icon.ChevronsUp color={"white"} size={16} />} label={"Highest"} />,
}

const EditablePriorityField = ({priorityEnum, handleFn, clickable}) => {

    if (!priorityEnum) {
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

    const wrappedHandleFn = (val) => {
        setEdit(false)
        handleFn(val)
    }

    const [edit, setEdit] = useState(false);

    if (edit) {
        return (
            <IssuePriorityEnumPickerField selectedEnum={priorityEnum} handleFn={(val) => wrappedHandleFn(val)}/>
        )
    } else {
        return (
            <Button color="primary" onClick={clickable ? handleClick : (e) => {}}>Priority: {priorityMap[priorityEnum]}</Button>
        )
    }
}

export default EditablePriorityField
