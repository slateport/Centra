import React from 'react'
import ReactTimeAgo from 'react-time-ago'
import TimeAgo from 'javascript-time-ago'
import en from 'javascript-time-ago/locale/en'

TimeAgo.addDefaultLocale(en)

export const RoundTimeAgo = ({ date }) => (
    <React.Fragment>
        <ReactTimeAgo date={date} timeStyle="round"/>
    </React.Fragment>
)
