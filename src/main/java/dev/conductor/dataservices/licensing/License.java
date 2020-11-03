package dev.conductor.dataservices.licensing;

public class License {

    private final int numberOfUser;
    private final String licenseEdition;
    private final String licenseId;
    private final boolean isEvaluation;
    private final String licenseString;

    public License(int numberOfUser, String licenseEdition, String licenseId, boolean isEvaluation, String licenseString) {
        this.numberOfUser = numberOfUser;
        this.licenseEdition = licenseEdition;
        this.licenseId = licenseId;
        this.isEvaluation = isEvaluation;
        this.licenseString = licenseString;
    }

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public String getLicenseEdition() {
        return licenseEdition;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public boolean isEvaluation() {
        return isEvaluation;
    }

    public String getLicenseString() {
        return licenseString;
    }

    @Override
    public String toString() {
        return "License{" +
                "numberOfUser=" + numberOfUser +
                ", licenseEdition='" + licenseEdition + '\'' +
                ", licenseId='" + licenseId + '\'' +
                ", isEvaluation=" + isEvaluation +
                '}';
    }
}
