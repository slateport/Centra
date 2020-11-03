package dev.conductor.dataservices.dto;

public class InstallationDTO {

    private String initialAdminUsername;
    private String initialAdminPassword;
    private String licenseKey;

    public String getInitialAdminUsername() {
        return initialAdminUsername;
    }

    public String getInitialAdminPassword() {
        return initialAdminPassword;
    }

    public String getLicenseKey() {
        return licenseKey;
    }
}
