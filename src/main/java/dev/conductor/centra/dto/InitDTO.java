package dev.conductor.centra.dto;

public class InitDTO {
    private final Boolean registrationEnabled;
    private final Boolean instancePrivate;
    private final Boolean installationComplete;
    private final String publicName;

    public InitDTO(Boolean registrationEnabled, Boolean instancePrivate, Boolean installationComplete, String publicName) {
        this.registrationEnabled = registrationEnabled;
        this.instancePrivate = instancePrivate;
        this.installationComplete = installationComplete;
        this.publicName = publicName;
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
}
