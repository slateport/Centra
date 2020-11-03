package dev.conductor.dataservices.service;

import dev.conductor.dataservices.licensing.License;

public interface LicenseService {

    License getLicense();
    License saveLicense(String licenseKey);

}
