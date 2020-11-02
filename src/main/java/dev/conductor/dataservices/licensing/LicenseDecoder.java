package dev.conductor.dataservices.licensing;

public interface LicenseDecoder {

    License decode(String licenseString);
}
