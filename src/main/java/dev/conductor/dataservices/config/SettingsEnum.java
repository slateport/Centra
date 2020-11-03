package dev.conductor.dataservices.config;

public enum SettingsEnum {
    INSTALLATION_COMPLETE("instance.installation_complete"),
    INSTANCE_NAME("instance.public_name"),
    PRIVATE_INSTANCE("instance.private"),
    ALLOW_REGISTRATION("instance.registration.enabled");

    private final String settingsKey;

    private SettingsEnum(String settingsKey) {
        this.settingsKey = settingsKey;
    }

    @Override
    public String toString(){
        return settingsKey;
    }
}
