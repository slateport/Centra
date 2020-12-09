import React from 'react';
import {Button as MuiButton, Menu, MenuItem} from '@material-ui/core';
import SupervisorAccountIcon from '@material-ui/icons/SupervisorAccount';
import styled from "styled-components";

const Button = styled(MuiButton)`
    color : ${props=> props.theme.header.color}
`;


export default function AdminMenu() {
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
                <SupervisorAccountIcon />
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
                }} component={"a"} href={"/admin"}>General Settings
                </MenuItem>
                <MenuItem onClick={() => {
                    handleClose()
                }} component={"a"} href={"/admin/projects"}>Manage Projects
                </MenuItem>
            </Menu>
        </React.Fragment>
    );
}