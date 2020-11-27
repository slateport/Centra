import React, {useEffect, useState} from "react";
import {issue} from "../../../services";
import ProjectIssueTypePickerField from "../../../components/ProjectIssueTypePickerField";
import {Button, Chip} from "@material-ui/core";
import * as Icon from "react-feather";
import styled from "styled-components";
import {green, red, yellow} from "@material-ui/core/colors";

const typePromise = (id) => issue.getIssueTypeById(id)

const GreenChip = styled(Chip)`
  background-color: ${green[700]};
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

const iconMap = {
    "AlertTriangle": (text) => <YellowChip icon={<Icon.AlertTriangle color={"white"} size={16}/>} label={text} />,
    "Zap": (text) => <GreenChip icon={<Icon.Zap color={"white"} size={16} />} label={text} />,
    "ArrowUp": (text) => <GreenChip icon={<Icon.ArrowUp  color={"white"} size={16}/>} label={text} />,
    "Plus": (text) => <GreenChip icon={<Icon.Plus color={"white"} size={16} />} label={text} />,
    "FileText": (text) => <RedChip icon={<Icon.FileText color={"white"} size={16} />} label={text} />,
    "Copy": (text) => <RedChip icon={<Icon.Copy color={"white"} size={16} />} label={text} />,
    "CheckSquare": (text) => <YellowChip icon={<Icon.CheckSquare color={"white"} size={16} />} label={text} />,
}

const EditableIssueTypeField = ({id, handleFn, clickable, projectKey}) => {
    if(id == null){
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

    const [issueType, setIssueType] = useState(null);
    const [edit, setEdit] = useState(false);


    useEffect(() => {
        typePromise(id).then(data => setIssueType(data))
    }, []);

    const wrappedHandleFn = (val) => {
        setEdit(false)
        handleFn(val)
    }


    if (edit) {
        return (
            <ProjectIssueTypePickerField selectedId={id} projectKey={projectKey} handleFn={(val) => wrappedHandleFn(val)}/>
        )
    } else {
        return (issueType == null) ? (<span>Unknown</span>) : (
            <Button color="primary" onClick={clickable ? handleClick : (e) => {}}>Type: {iconMap[issueType.icon](issueType.label)}</Button>
        )
    }
}



export default EditableIssueTypeField