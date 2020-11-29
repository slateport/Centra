package dev.conductor.centra.data.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import dev.conductor.centra.domain.settings.SettingsEnum;
import dev.conductor.centra.data.defualt.DefaultInstall;
import dev.conductor.centra.domain.issue.IssuePrioritySchemaService;
import dev.conductor.centra.domain.issue.IssueTypeSchemaService;
import dev.conductor.centra.domain.project.ProjectService;
import dev.conductor.centra.domain.settings.SettingsService;
import dev.conductor.centra.domain.settings.entity.Settings;
import dev.conductor.centra.domain.workflow.WorkflowService;

import java.util.EnumSet;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "databaseInitialisation", author = "CSF")
    public void databaseInitialisation(
            SettingsService settingsService,
            IssueTypeSchemaService issueTypeSchemaService,
            WorkflowService workflowService,
            ProjectService projectService,
            IssuePrioritySchemaService prioritySchemaService
    ) {
        updateSettings(settingsService);
        DefaultInstall install = new DefaultInstall(issueTypeSchemaService, workflowService, projectService, prioritySchemaService);
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
