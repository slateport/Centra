import React from "react";
import styled from "styled-components";
import { green, blue, yellow } from "@material-ui/core/colors";

import {
    Chip,
} from "@material-ui/core";

const GreenChip = styled(Chip)`
  background-color: ${green[700]};
  border-radius: 5px;
  color: ${props => props.theme.palette.common.white};
  font-size: 60%;
  height: 20px;
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
  height: 20px;
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

const BlueChip = styled(Chip)`
  background-color: ${yellow[700]};
  border-radius: 5px;
  color: ${props => props.theme.palette.common.white};
  font-size: 60%;
  height: 20px;
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

const StatusChip = ({issue}) => {
    console.log(issue.workflow)
    if (issue.workflowState.isTerminus){
        return (
            <GreenChip label={issue.workflowState.label}/>
        )
    } else if (!issue.workflowState.isTerminus && !issue.workflowState.entry){
        return (
            <YellowChip label={issue.workflowState.label}/>
        )
    } else {
        return (
            <BlueChip label={issue.workflowState.label}/>
        )
    }
}

export default StatusChip