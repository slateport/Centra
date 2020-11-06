export const init = {
    getInit,
};

function getInit() {
    return fetch(`/api/init`)
    .then(initData => initData.json());
}
