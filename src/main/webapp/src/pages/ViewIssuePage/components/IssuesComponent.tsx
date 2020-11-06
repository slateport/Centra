import React, {useEffect, useState} from "react";
import {
    Accordion,
    AccordionDetails,
    AccordionSummary, Typography,
} from "@material-ui/core";
import { ExpandMore as ExpandMoreIcon } from "@material-ui/icons";
import {user} from "../../../services";
import {Link} from "react-router-dom";

const userPromise = (userId) => user.getUser(userId)

const IssueComment = ({comment: commentDto}) => {
    const [user, setUser] = useState(null);
    useEffect(() => {
        userPromise(commentDto.createdByUserId).then(data => setUser(data))
    }, []);


    return (user == null) ? (<span>User Data unfilled</span>) : (
        <Accordion expanded={true}>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>
                    <Link to={`/users/${user.id}`}>{user.displayName}</Link> added a comment - x hours ago</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography>{commentDto.text}</Typography>
            </AccordionDetails>
        </Accordion>
    )
}

export default IssueComment