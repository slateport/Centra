package dev.conductor.centra.dto;

import java.util.Optional;

public class InitDTO {
    private final Boolean registrationEnabled;
    private final Boolean instancePrivate;
    private final Boolean installationComplete;
    private final String publicName;
    private final UserLiteDTO user;

    public InitDTO(Boolean registrationEnabled, Boolean instancePrivate, Boolean installationComplete, String publicName, UserLiteDTO user) {
        this.registrationEnabled = registrationEnabled;
        this.instancePrivate = instancePrivate;
        this.installationComplete = installationComplete;
        this.publicName = publicName;
        this.user = user;
    }

    public Boolean getRegistrationEnabled() {
        return registrationEnabled;
    }

    public Boolean getInstancePrivate() {
        return instancePrivate;
    }

    public Boolean getInstallationComplete() {
        return installationComplete;
    }

    public String getPublicName() {
        return publicName;
    }

    public UserLiteDTO getUser() {
        return user;
    }
}
