package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.entities.Settings;
import dev.conductor.dataservices.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping()
    public HashMap<String, String> getInit() {
        HashMap<String, String> initData = new HashMap<>();

        initData.put(
                SettingsEnum.INSTANCE_NAME.toString(),
                getSettings(SettingsEnum.INSTANCE_NAME)
        );

        initData.put(
                SettingsEnum.ALLOW_REGISTRATION.toString(),
                getSettings(SettingsEnum.ALLOW_REGISTRATION)
        );

        initData.put(
                SettingsEnum.PRIVATE_INSTANCE.toString(),
                getSettings(SettingsEnum.PRIVATE_INSTANCE)
        );

        return initData;
    }

    private String getSettings(SettingsEnum settings) {
        Settings retrievedValue = settingsService.getSettingsByName(settings);

        if (retrievedValue == null) {
            return settingsService.getDefaultByName(settings).getValue();
        }

        return retrievedValue.getValue();
    }
}
