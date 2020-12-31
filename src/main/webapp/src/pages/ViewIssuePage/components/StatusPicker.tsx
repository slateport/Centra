import {Button as MuiButton, Menu, MenuItem} from "@material-ui/core";
import StatusChip from "./StatusChip";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";
import React from "react";
import styled from "styled-components";
import {isAuthenticated} from "../../../helpers";

const Button = styled(MuiButton)`
    padding-left:0
`

const StatusPicker = ({issue, workflowTransitions, onTransitionIssue, props}) => {

    const [anchorElTransitionMenu, setAnchorElTransitionMenu] = React.useState<null | HTMLElement>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorElTransitionMenu(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorElTransitionMenu(null);
    };

    return (
        <React.Fragment>
            <Button
                color="primary"
                onClick={handleClick}
                disabled={workflowTransitions.length == 0 || !isAuthenticated()}
            >
                { issue.workflowState &&
                <StatusChip label={issue.workflowState.label} isInitial={issue.workflowState.entry} isTerminus={issue.workflowState.isTerminus} />
                }
                <KeyboardArrowDownIcon />
            </Button>
            <Menu
                id="transition-menu"
                anchorEl={anchorElTransitionMenu}
                keepMounted
                getContentAnchorEl={null}
                anchorOrigin={{vertical: "bottom", horizontal: "center"}}
                transformOrigin={{vertical: "top", horizontal: "center"}}
                open={Boolean(anchorElTransitionMenu)}
                onClose={handleClose}
                color={"primary"}
            >
                {workflowTransitions.map(transition =>
                    <MenuItem onClick={onTransitionIssue(props, issue, transition)} key={transition.label}>
                        <StatusChip label={transition.label} isInitial={transition.initial} isTerminus={transition.terminus} />
                    </MenuItem>
                )}
            </Menu>
        </React.Fragment>
    )
}

export { StatusPicker }