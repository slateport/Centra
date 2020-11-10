import React from 'react';
import { Button, Menu, MenuItem } from '@material-ui/core';
import { history } from '../../helpers'
import {Link} from "react-router-dom";

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
            <Button color="primary" aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
        Issues
            </Button>
            <Menu
            id="simple-menu"
            anchorEl={anchorEl}
            keepMounted
            open={Boolean(anchorEl)}
            onClose={handleClose}
            >
            <MenuItem onClick={() => {
                handleClose()
            }} component={"a"} href={"/search"}>Search for issues</MenuItem>
        </Menu>
        </React.Fragment>
);
}