import UserLite from "./UserLite"

interface Init {
    registrationEnabled: boolean,
    instancePrivate: boolean,
    installationComplete: boolean,
    publicName: string,
    user: UserLite
}

export default Init