import React from "react";
import styled from "styled-components";
import {blueGrey, green, yellow} from "@material-ui/core/colors";

import {Chip,} from "@material-ui/core";

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

const StatusChip = ({issue}) => {
    if (issue.workflowState.isTerminus){
        return (
            <React.Fragment>
                <GreenChip label={issue.workflowState.label}/>
            </React.Fragment>
        )
    } else if (!issue.workflowState.isTerminus && !issue.workflowState.entry){
        return (
            <React.Fragment>
                <YellowChip label={issue.workflowState.label}/>
            </React.Fragment>
        )
    } else {
        return (
            <React.Fragment>
                <BlueChip label={issue.workflowState.label}/>
            </React.Fragment>
        )
    }
}

export default StatusChip