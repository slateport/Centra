import {issue} from "../../../services";
import React, {useEffect, useState} from "react";
import UserPickerField from "../../../components/UserPickerField";

const userPromise = (id) => issue.getIssueTypeById(id).then(r => r.json())

const EditableIssueTypeField = ({id, handleFn, clickable}) => {
    if(id == null){
        return (<span>Unknown</span>)
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

    const [issueType, setIssueType] = useState(null);
    const [edit, setEdit] = useState(false);


    useEffect(() => {
        userPromise(id).then(data => setIssueType(data))
    }, []);

    const wrappedHandleFn = (val) => {
        setEdit(false)
        handleFn(val)
    }


    if (edit) {
        return (
            <UserPickerField handleFn={wrappedHandleFn} userId={issueType.id} />
        )
    } else {
        return (issueType == null) ? (<span>Unknown</span>) : (
            <span onClick={clickable ? handleClick : (e) => {}}>{issueType.label}</span>
        )
    }
}



export default EditableIssueTypeField