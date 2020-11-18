package dev.conductor.centra.data.defualt;

import dev.conductor.centra.entities.*;
import dev.conductor.centra.service.IssueTypeSchemaService;
import dev.conductor.centra.service.WorkflowService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultInstall {

    private final IssueTypeSchemaService issueTypeSchemaService;
    private final WorkflowService workflowService;

    public DefaultInstall(IssueTypeSchemaService issueTypeSchemaService, WorkflowService workflowService) {
        this.issueTypeSchemaService = issueTypeSchemaService;
        this.workflowService = workflowService;
    }

    public void createDefaultEntities(){
        createIssueTypeAndSchema();
    }

    protected void createIssueTypeAndSchema(){

        IssueType bug = issueTypeSchemaService.createType(new IssueType("Bug"));
        IssueType epic = issueTypeSchemaService.createType(new IssueType("Epic"));
        IssueType improvement = issueTypeSchemaService.createType(new IssueType("Improvement"));
        IssueType newFeature = issueTypeSchemaService.createType(new IssueType("New Feature"));
        IssueType story = issueTypeSchemaService.createType(new IssueType("Story"));
        IssueType subTask = issueTypeSchemaService.createType(new IssueType("Sub-task"));
        IssueType task = issueTypeSchemaService.createType(new IssueType("Task"));

        IssueTypeSchema schema = new IssueTypeSchema(Project.DEFAULT_ISSUE_TYPE_SCHEMA_NAME);
        schema.addIssueType(bug);
        schema.addIssueType(epic);
        schema.addIssueType(improvement);
        schema.addIssueType(newFeature);
        schema.addIssueType(story);
        schema.addIssueType(subTask);
        schema.addIssueType(task);

        issueTypeSchemaService.createSchema(schema);

        List<WorkflowState> states = new ArrayList<>();
        states.add(new WorkflowState(true, false, "TO DO"));
        states.add(new WorkflowState(false, false, "IN PROGRESS"));
        states.add(new WorkflowState(false, true, "DONE"));

        List<WorkflowTransition> transitions = new ArrayList<>();
        transitions.add(new WorkflowTransition("TO DO", "IN PROGRESS", "In Progress", true));
        transitions.add(new WorkflowTransition("IN PROGRESS", "DONE", "Done", false));
        transitions.add(new WorkflowTransition("DONE", "TODO", "Reopen", false));

        Workflow wfl = new Workflow(Project.DEFAULT_WORKFLOW_NAME, states, transitions);

        workflowService.save(wfl);
    }
}