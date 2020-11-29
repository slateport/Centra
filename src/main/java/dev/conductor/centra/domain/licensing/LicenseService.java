package dev.conductor.centra.domain.licensing;

public interface LicenseService {

    License getLicense();
    License saveLicense(String licenseKey);

}
