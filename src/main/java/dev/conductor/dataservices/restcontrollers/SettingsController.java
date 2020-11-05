package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.entities.Settings;
import dev.conductor.dataservices.service.ApplicationUserService;
import dev.conductor.dataservices.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.EnumSet;
import java.util.HashMap;

@RestController
@RequestMapping("/api/settings")
public class SettingsController extends BaseController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping
    public HashMap<String, String> listAll(Principal principal) {
        HashMap<String, String> map = new HashMap<>();

        if (!applicationUserService.isAdmin(principal)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        EnumSet<SettingsEnum> definitions = EnumSet.allOf(SettingsEnum.class);

        for (SettingsEnum settings: definitions) {
            map.put(settings.toString(), getSettings(settings));
        }

        return map;
    }

    private String getSettings(SettingsEnum settings) {
        Settings retrievedValue = settingsService.getSettingsByName(settings);

        if (retrievedValue == null) {
            return settingsService.getDefaultByName(settings).getValue();
        }

        return retrievedValue.getValue();
    }
}
