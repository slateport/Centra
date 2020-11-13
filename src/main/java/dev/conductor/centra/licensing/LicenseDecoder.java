package dev.conductor.centra.licensing;

public interface LicenseDecoder {

    License decode(String licenseString);
}
