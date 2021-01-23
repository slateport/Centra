import React from 'react'
import ViewAgendaIcon from '@material-ui/icons/ViewAgenda'
import {Button as MuiButton, Menu, MenuItem} from "@material-ui/core";
import styled from "styled-components";

const Button = styled(MuiButton)`
    color : ${props=> props.theme.header.color}
`;

export default function IssueMenu() {
    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <React.Fragment>
            <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                <ViewAgendaIcon />
            </Button>
            <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
                getContentAnchorEl={null}
                anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
                transformOrigin={{ vertical: "top", horizontal: "center" }}
            >
                <MenuItem onClick={() => {
                    handleClose()
                }} component={"a"} href={"/boards"}>Manage Boards</MenuItem>
            </Menu>
        </React.Fragment>
    );
}