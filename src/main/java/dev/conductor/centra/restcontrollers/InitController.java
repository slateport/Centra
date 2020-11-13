package dev.conductor.centra.restcontrollers;

import dev.conductor.centra.config.SettingsEnum;
import dev.conductor.centra.dto.InitDTO;
import dev.conductor.centra.entities.Settings;
import dev.conductor.centra.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/init")
public class InitController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping()
    public InitDTO getInit() {
        return new InitDTO(
                Boolean.valueOf(getSettingsValue(SettingsEnum.ALLOW_REGISTRATION)),
                Boolean.valueOf(getSettingsValue(SettingsEnum.PRIVATE_INSTANCE)),
                Boolean.valueOf(getSettingsValue(SettingsEnum.INSTALLATION_COMPLETE)),
                getSettingsValue(SettingsEnum.INSTANCE_NAME)
        );
    }

    private String getSettingsValue(SettingsEnum settings) {
        Settings retrievedValue = settingsService.getSettingsByName(settings);

        if (retrievedValue == null) {
            return settingsService.getDefaultByName(settings).getValue();
        }

        return retrievedValue.getValue();
    }
}
