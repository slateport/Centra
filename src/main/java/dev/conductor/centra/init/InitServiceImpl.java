package dev.conductor.centra.init;

import dev.conductor.centra.config.SettingsEnum;
import dev.conductor.centra.dto.UserLiteDTO;
import dev.conductor.centra.entities.Settings;
import dev.conductor.centra.service.ApplicationUserService;
import dev.conductor.centra.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ApplicationUserService userService;

    @Override
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
