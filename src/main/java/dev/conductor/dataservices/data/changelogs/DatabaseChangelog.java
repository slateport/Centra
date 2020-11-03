package dev.conductor.dataservices.data.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.entities.Settings;
import dev.conductor.dataservices.service.SettingsService;

import java.util.EnumSet;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "databaseInitialisation", author = "CSF")
    public void databaseInitialisation(
            SettingsService settingsService
    ) {
        EnumSet<SettingsEnum> definitions = EnumSet.allOf(SettingsEnum.class);

        for (SettingsEnum settings: definitions) {
            Settings retrievedValue = settingsService.getSettingsByName(settings);

            if (retrievedValue == null){
                Settings entity = new Settings(
                        settings.name(),
                        settingsService.getDefaultByName(settings).getValue()
                );

                settingsService.save(entity);
            }
        }
    }
}
