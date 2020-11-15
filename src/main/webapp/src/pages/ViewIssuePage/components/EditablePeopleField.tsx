import {user} from "../../../services";
import React, {useEffect, useState} from "react";
import UserPickerField from "../../../components/UserPickerField";

const userPromise = (userId) => user.getUser(userId)

const EditablePeopleField = ({userId, handleFn, clickable}) => {
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
            <span onClick={clickable ? handleClick : (e) => {}}>{user.displayName}</span>
        )
    }
}



export default EditablePeopleField