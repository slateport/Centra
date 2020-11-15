package dev.conductor.centra.restcontrollers;

import dev.conductor.centra.config.SettingsEnum;
import dev.conductor.centra.dto.InitDTO;
import dev.conductor.centra.dto.UserLiteDTO;
import dev.conductor.centra.entities.Settings;
import dev.conductor.centra.service.ApplicationUserService;
import dev.conductor.centra.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/api/init")
public class InitController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ApplicationUserService userService;

    @GetMapping()
    public InitDTO getInit(Principal principal) {

        Optional<UserLiteDTO> optionalUser = userService.findAllLite().stream().filter(userLiteDTO ->
                principal != null && principal.getName().equals(userLiteDTO.getUsername())
        ).findFirst();

        return new InitDTO(
                Boolean.valueOf(getSettingsValue(SettingsEnum.ALLOW_REGISTRATION)),
                Boolean.valueOf(getSettingsValue(SettingsEnum.PRIVATE_INSTANCE)),
                Boolean.valueOf(getSettingsValue(SettingsEnum.INSTALLATION_COMPLETE)),
                getSettingsValue(SettingsEnum.INSTANCE_NAME),
                optionalUser.isEmpty() ? null : optionalUser.get()
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
