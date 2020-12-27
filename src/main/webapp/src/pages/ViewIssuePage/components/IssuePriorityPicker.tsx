import React, {useEffect, useState} from "react";
import {Button as MuiButton, Chip, Menu, MenuItem} from "@material-ui/core";
import {project} from "../../../services";
import * as Icon from "react-feather";
import styled from "styled-components";
import {blueGrey, red, yellow} from "@material-ui/core/colors";
import {isAuthenticated} from "../../../helpers";

const Button = styled(MuiButton)`
    padding-left:0
`

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
    "ChevronsDown": (priority) => <BlueChip icon={<Icon.ChevronsDown color={"white"} size={16}/>} />,
    "ChevronDown": (priority) =><BlueChip icon={<Icon.ChevronDown color={"white"} size={16} />} />,
    "Code": (priority) => <YellowChip icon={<Icon.Code  color={"white"} size={16}/>} />,
    "ChevronUp": (priority) => <RedChip icon={<Icon.ChevronUp color={"white"} size={16} />} />,
    "ChevronsUp": (priority) => <RedChip icon={<Icon.ChevronsUp color={"white"} size={16} />} />,
    undefined: <RedChip icon={<Icon.ChevronsUp color={"white"} size={16} />}/>
}

const IssuePriorityPicker = ({preText, postText, issuePriorityId, projectKey, onClickEvent}) => {
    const [anchorElTransitionMenu, setAnchorElTransitionMenu] = React.useState<null | HTMLElement>(null);
    const [priority, setIssuePriority] = useState({ icon: 'Code', label: 'Foo'});
    const [issuePriorityList, setIssuePriorityList] = useState([]);

    useEffect(() => {
        priorityPromise(issuePriorityId)
            .then(data => setIssuePriority(data))

        project.getPrioritiesForProject(projectKey)
            .then(list => setIssuePriorityList(list))
    }, []);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorElTransitionMenu(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorElTransitionMenu(null);
    };

    if (priority) {
        return (
            <React.Fragment>
                <Button color="primary"
                        onClick={handleClick}
                        disabled={!isAuthenticated()}
                >{preText} {typeof priorityMap[(priority || {}).icon] === 'function' ? priorityMap[(priority || {}).icon](priority) : null}&nbsp;{priority.label} {postText}</Button>
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

                    {issuePriorityList.map((priorityType) =>
                        <MenuItem key={priorityType.id} onClick={() => onClickEvent(priorityType.id)}>
                            {typeof priorityMap[(priorityType || {}).icon] === 'function' ? priorityMap[(priorityType || {}).icon](priorityType) : null}&nbsp;{priorityType.label}
                        </MenuItem>
                    )}
                </Menu>
            </React.Fragment>
        )
    }
}

export { IssuePriorityPicker }