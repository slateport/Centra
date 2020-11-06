package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.dto.InstallationDTO;
import dev.conductor.dataservices.entities.ApplicationUser;
import dev.conductor.dataservices.entities.Settings;
import dev.conductor.dataservices.service.ApplicationUserService;
import dev.conductor.dataservices.service.LicenseService;
import dev.conductor.dataservices.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/install")
public class InstallController {

    @Autowired
    SettingsService settingsService;

    @Autowired
    ApplicationUserService userService;

    @Autowired
    LicenseService licenseService;

    @PostMapping()
    public void install(@RequestBody InstallationDTO installationDTO) {

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
