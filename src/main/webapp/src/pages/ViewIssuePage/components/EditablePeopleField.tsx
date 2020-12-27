import {user} from "../../../services";
import React, {useEffect, useState} from "react";
import UserPickerField from "../../../components/UserPickerField";
import {Avatar, Button as MuiButton} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import styled from "styled-components";

const Button = styled(MuiButton)`
    padding-left:0
`

const userPromise = (userId) => user.getUser(userId)

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        '& > *': {
            margin: theme.spacing(1),
        },
    },
    small: {
        width: theme.spacing(6),
        height: theme.spacing(6),
    }
}));

const EditablePeopleField = ({userId, handleFn, clickable}) => {
    const classes = useStyles();

    if(userId == null){
        return <span>Unassigned</span>
    }

    let count = 0;
    let timeout = 250;

    const handleClick = (e) => {
        if (timeout) clearTimeout(timeout)
        count++

        timeout = setTimeout(() => {
            if (count === 2) {
                setEdit(true)
            }

            // reset count
            count = 0
        }, 250)
    }

    const [user, setUser] = useState(null);
    const [edit, setEdit] = useState(false);


    useEffect(() => {
        userPromise(userId).then(data => setUser(data))
    }, []);

    const wrappedHandleFn = (val) => {
        setEdit(false)
        handleFn(val)
    }


    if (edit) {
        return (
            <UserPickerField handleFn={wrappedHandleFn} userId={userId} />
        )
    } else {
        return (user == null) ? (<span>Unknown</span>) : (
            <Button onClick={clickable ? handleClick : (e) => {}}><Avatar className={classes.small}/>&nbsp;{user.displayName}</Button>
        )
    }
}



export default EditablePeopleField