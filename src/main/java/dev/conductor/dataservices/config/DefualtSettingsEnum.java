package dev.conductor.dataservices.config;

public enum DefualtSettingsEnum {
    INSTALLATION_COMPLETE("false"),
    INSTANCE_NAME("Conductor"),
    PRIVATE_INSTANCE("true"),
    ALLOW_REGISTRATION("true");

    private final String settingsKey;

    private DefualtSettingsEnum(String settingsKey) {
        this.settingsKey = settingsKey;
    }

    @Override
    public String toString(){
        return settingsKey;
    }
}
