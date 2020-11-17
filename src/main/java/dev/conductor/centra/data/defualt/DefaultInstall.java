package dev.conductor.centra.data.defualt;

import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.entities.IssueTypeSchema;
import dev.conductor.centra.service.IssueTypeSchemaService;
import org.springframework.stereotype.Component;

@Component
public class DefaultInstall {

    private final IssueTypeSchemaService issueTypeSchemaService;

    public DefaultInstall(IssueTypeSchemaService issueTypeSchemaService) {
        this.issueTypeSchemaService = issueTypeSchemaService;
    }

    public void createDefaultEntities(){
        createIssueTypeAndSchema();
    }

    protected void createIssueTypeAndSchema(){
        IssueTypeSchema schema = new IssueTypeSchema("Default Issue Type Schema");
        issueTypeSchemaService.createSchema(schema);

        IssueType bug = issueTypeSchemaService.createType(new IssueType("Bug", schema.getId()));
        IssueType epic = issueTypeSchemaService.createType(new IssueType("Epic", schema.getId()));
        IssueType improvement = issueTypeSchemaService.createType(new IssueType("Improvement", schema.getId()));
        IssueType newFeature = issueTypeSchemaService.createType(new IssueType("New Feature", schema.getId()));
        IssueType story = issueTypeSchemaService.createType(new IssueType("Story", schema.getId()));
        IssueType subTask = issueTypeSchemaService.createType(new IssueType("Sub-task", schema.getId()));
        IssueType task = issueTypeSchemaService.createType(new IssueType("Task", schema.getId()));
    }
}