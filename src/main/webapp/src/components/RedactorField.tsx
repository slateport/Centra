import React from "react";
import '../static/redactorx/redactorx.min.css'
import RedactorX from '../static/redactorx/redactorx';


const RedactorField = (props, rest) => {
    const randomUuid = randomUuidString()
    RedactorX('#'+randomUuid)
    return (
        <React.Fragment>
            <textarea id={randomUuid}{...rest} defaultValue={props.value} />
        </React.Fragment>
    )
}


const  randomUuidString = () => {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
export default RedactorField