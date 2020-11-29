package dev.conductor.centra.domain.installation;

import dev.conductor.centra.domain.settings.SettingsEnum;
import dev.conductor.centra.domain.applicationUser.ApplicationUserService;
import dev.conductor.centra.domain.installation.dto.InstallationDTO;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.settings.entity.Settings;
import dev.conductor.centra.domain.licensing.LicenseService;
import dev.conductor.centra.domain.settings.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InstallServiceImpl implements InstallService {

    @Autowired
    SettingsService settingsService;

    @Autowired
    ApplicationUserService userService;

    @Autowired
    LicenseService licenseService;

    @Override
    public void install(InstallationDTO installationDTO) {
        Settings installComplete = settingsService.getSettingsByName(SettingsEnum.INSTALLATION_COMPLETE);

        if (!installComplete.getValue().equals("false")) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Installation is already complete"
            );
        }

        if (installationDTO.getLicenseKey() != null) {
            licenseService.saveLicense(installationDTO.getLicenseKey());
        }

        userService.createUser(new ApplicationUser(
                installationDTO.getUsername(),
                "",
                installationDTO.getPassword(),
                installationDTO.getUsername(),
                true,
                true
        ));

        settingsService.save(new Settings(
                installComplete.getId(),
                installComplete.getKey(),
                "true"
        ));
    }
}
