import React, {useEffect, useState} from "react";
import {Button as MuiButton, Chip, Menu, MenuItem} from "@material-ui/core";
import {issue, project} from "../../../services";
import * as Icon from "react-feather";
import styled from "styled-components";
import {green, red, yellow} from "@material-ui/core/colors";
import {isAuthenticated} from "../../../helpers";

const Button = styled(MuiButton)`
    padding-left:0
`

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

const typePromise = (id) => issue.getIssueTypeById(id)

export const iconMap = {
    "AlertTriangle": (text) => <YellowChip icon={<Icon.AlertTriangle color={"white"} size={16}/>} label={text}/>,
    "Zap": (text) => <GreenChip icon={<Icon.Zap color={"white"} size={16}/>} label={text}/>,
    "ArrowUp": (text) => <GreenChip icon={<Icon.ArrowUp color={"white"} size={16}/>} label={text}/>,
    "Plus": (text) => <GreenChip icon={<Icon.Plus color={"white"} size={16}/>} label={text}/>,
    "FileText": (text) => <RedChip icon={<Icon.FileText color={"white"} size={16}/>} label={text}/>,
    "Copy": (text) => <RedChip icon={<Icon.Copy color={"white"} size={16}/>} label={text}/>,
    "CheckSquare": (text) => <YellowChip icon={<Icon.CheckSquare color={"white"} size={16}/>} label={text}/>,
    undefined: (text) => <YellowChip icon={<Icon.CheckSquare color={"white"} size={16}/>} label={text}/>
}


const IssueTypePicker = ({preText, postText, issueTypeId, projectKey, onClickEvent}) => {
    const [anchorElTransitionMenu, setAnchorElTransitionMenu] = React.useState<null | HTMLElement>(null);
    const [issueType, setIssueType] = useState({ icon: 'Zap', label: 'Foo'});
    const [issueTypeList, setIssueTypeList] = useState([]);

    useEffect(() => {
        typePromise(issueTypeId)
            .then(data => setIssueType(data))

        project.getIssueTypesForProject(projectKey)
            .then(issueTypes => setIssueTypeList(issueTypes))
    }, []);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorElTransitionMenu(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorElTransitionMenu(null);
    };

    if (issueType) {
        return (
            <React.Fragment>
                <Button color="primary"
                        onClick={handleClick}
                        disabled={!isAuthenticated()}
                >{preText} {iconMap[issueType.icon]()}&nbsp;{issueType.label} {postText}</Button>
                <Menu
                    id="issueType-menu"
                    anchorEl={anchorElTransitionMenu}
                    keepMounted
                    getContentAnchorEl={null}
                    anchorOrigin={{vertical: "bottom", horizontal: "center"}}
                    transformOrigin={{vertical: "top", horizontal: "center"}}
                    open={Boolean(anchorElTransitionMenu)}
                    onClose={handleClose}
                    color={"primary"}
                >

                    {issueTypeList.map((issueType) =>
                        <MenuItem key={issueType.id} onClick={() => onClickEvent(issueType.id)}>
                            {iconMap[issueType.icon]("")}&nbsp;{issueType.label}
                        </MenuItem>
                    )}
                </Menu>
            </React.Fragment>
        )
    }
}

export { IssueTypePicker }