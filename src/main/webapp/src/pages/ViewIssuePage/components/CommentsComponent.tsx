import React, {useEffect, useState} from "react";
import parse from 'html-react-parser';

import {Accordion, AccordionDetails, AccordionSummary, Divider as MuiDivider, Typography,} from "@material-ui/core";
import {ExpandMore as ExpandMoreIcon} from "@material-ui/icons";
import {user} from "../../../services";
import {Link} from "react-router-dom";
import {RoundTimeAgo} from "../../../components/RoundTimeAgo";
import styled from "styled-components";
import {spacing} from "@material-ui/system";

const userPromise = (userId) => user.getUserLite(userId)
const Divider = styled(MuiDivider)(spacing);
const IssueComment = ({comment: commentDto}) => {
    const [user, setUser] = useState(null);
    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };

    useEffect(() => {
        userPromise(commentDto.createdByUserId).then(data => setUser(data))
    }, []);


    return (user == null) ? (<span>User Data unfilled</span>) : (
        <React.Fragment>
            <Accordion expanded={expanded === commentDto.id} onChange={handleChange(commentDto.id)} id={commentDto.id}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                    <Typography>
                        <Link to={`/users/${user.id}`}>{user.displayName}</Link> added a comment &nbsp;
                        {commentDto.createdDate &&
                        <RoundTimeAgo date={new Date(commentDto.createdDate)} />
                        }
                    </Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>{parse(commentDto.text)}</Typography>
                </AccordionDetails>
            </Accordion>
            <Divider my={8} />
        </React.Fragment>
    )
}

export default IssueComment