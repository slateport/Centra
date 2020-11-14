import React from 'react';
import styled from "styled-components";
import {Button as MuiButton, Menu, MenuItem} from '@material-ui/core';
import {AccountCircle} from "@material-ui/icons";
import {history, isAuthenticated} from '../../helpers'

const Button = styled(MuiButton)`
    color : ${props=> props.theme.header.color}
`;

export default function MyProfileMenu() {
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
                    <AccountCircle />
            </Button>
            <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                {isAuthenticated() &&
                <MenuItem onClick={() => {
                    handleClose()
                }} component={"a"} href={"/login"}>Logout</MenuItem>
                }
                {!isAuthenticated() &&
                <MenuItem onClick={() => {
                    handleClose()
                }} component={"a"} href={"/login"}>Login</MenuItem>
                }
            </Menu>
        </React.Fragment>
    );
}