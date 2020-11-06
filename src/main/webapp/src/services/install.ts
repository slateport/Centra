export const install = {
    completeInstallation
}

function completeInstallation(username: string, password: string, licenseKey: string, ) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };


    return fetch(`/api/install`, requestOptions)
        .then(handleResponse)
}

function handleResponse(response: Response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            const error = (data && data.message) || JSON.parse(response.statusText);
            return Promise.reject(error);
        }

        return Promise.resolve();
    });
}