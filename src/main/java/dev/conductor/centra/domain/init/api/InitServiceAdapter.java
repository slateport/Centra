package dev.conductor.centra.domain.init.api;

import dev.conductor.centra.domain.settings.SettingsEnum;
import dev.conductor.centra.domain.init.dto.InitDTO;
import dev.conductor.centra.domain.applicationUser.dto.UserLiteDTO;
import dev.conductor.centra.domain.settings.entity.Settings;
import dev.conductor.centra.domain.applicationUser.api.ApplicationUserService;
import dev.conductor.centra.domain.settings.api.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class InitServiceAdapter implements InitService {

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
                optionalUser.isPresent() ? null : optionalUser.get()
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
