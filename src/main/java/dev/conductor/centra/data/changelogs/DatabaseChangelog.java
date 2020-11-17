package dev.conductor.centra.data.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import dev.conductor.centra.config.SettingsEnum;
import dev.conductor.centra.data.defualt.DefaultInstall;
import dev.conductor.centra.entities.Settings;
import dev.conductor.centra.service.IssueTypeSchemaService;
import dev.conductor.centra.service.SettingsService;

import java.util.EnumSet;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "databaseInitialisation", author = "CSF")
    public void databaseInitialisation(
            SettingsService settingsService,
            IssueTypeSchemaService issueTypeSchemaService
    ) {
        updateSettings(settingsService);
        DefaultInstall install = new DefaultInstall(issueTypeSchemaService);
        install.createDefaultEntities();
    }

    private void updateSettings(SettingsService settingsService) {
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
