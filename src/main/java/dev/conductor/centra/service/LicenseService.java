package dev.conductor.centra.service;

import dev.conductor.centra.licensing.License;

public interface LicenseService {

    License getLicense();
    License saveLicense(String licenseKey);

}
