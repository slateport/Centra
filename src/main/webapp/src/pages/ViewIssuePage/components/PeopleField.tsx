import {user} from "../../../services";
import React, {useEffect, useState} from "react";

const userPromise = (userId) => user.getUser(userId)

const PeopleField = ({userId}) => {
    if(userId == null){
        return <span>Unassigned</span>
    }

    const [user, setUser] = useState(null);
    useEffect(() => {
        userPromise(userId).then(data => setUser(data))
    }, []);


    return (user == null) ? (<span>Unknown</span>) : (
        <span>{user.displayName}</span>
    )
}

export default PeopleField