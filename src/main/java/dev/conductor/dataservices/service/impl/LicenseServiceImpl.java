package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.entities.Settings;
import dev.conductor.dataservices.licensing.License;
import dev.conductor.dataservices.licensing.LicenseDecoder;
import dev.conductor.dataservices.service.LicenseService;
import dev.conductor.dataservices.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {

    @Autowired
    SettingsService settingsService;

    @Autowired
    LicenseDecoder licenseDecoder;

    @Override
    public License getLicense() {
        Settings licenseKey = settingsService.getSettingsByName(SettingsEnum.LICENSE_KEY);

        if (licenseKey == null || licenseKey.getValue() == null){
            return null;
        }

        return licenseDecoder.decode(licenseKey.getValue());
    }

    @Override
    public License saveLicense(String licenseKey) {
        License decoded = licenseDecoder.decode(licenseKey);
        Settings licenseKeySettings = settingsService.getSettingsByName(SettingsEnum.LICENSE_KEY);

        settingsService.save(new Settings(
                licenseKeySettings.getId(),
                licenseKeySettings.getKey(),
                licenseKey
        ));

        return decoded;
    }
}
