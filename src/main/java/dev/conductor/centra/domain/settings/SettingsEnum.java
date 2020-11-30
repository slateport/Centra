package dev.conductor.centra.domain.settings;

public enum SettingsEnum {
    INSTALLATION_COMPLETE("instance.installation_complete"),
    INSTANCE_NAME("instance.public_name"),
    PRIVATE_INSTANCE("instance.private"),
    ALLOW_REGISTRATION("instance.registration.enabled"),
    LICENSE_KEY("instance.license_key");

    private final String settingsKey;

    private SettingsEnum(String settingsKey) {
        this.settingsKey = settingsKey;
    }

    @Override
    public String toString(){
        return settingsKey;
    }
}
