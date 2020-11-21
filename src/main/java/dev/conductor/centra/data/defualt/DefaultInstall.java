package dev.conductor.centra.data.defualt;

import dev.conductor.centra.entities.*;
import dev.conductor.centra.service.IssueTypeSchemaService;
import dev.conductor.centra.service.ProjectService;
import dev.conductor.centra.service.WorkflowService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultInstall {

    private final IssueTypeSchemaService issueTypeSchemaService;
    private final WorkflowService workflowService;
    private final ProjectService projectService;

    public DefaultInstall(
            IssueTypeSchemaService issueTypeSchemaService,
            WorkflowService workflowService,
            ProjectService projectService
            ) {
        this.issueTypeSchemaService = issueTypeSchemaService;
        this.workflowService = workflowService;
        this.projectService = projectService;
    }

    public void createDefaultEntities(){
        createIssueTypeAndSchema();
    }

    protected void createIssueTypeAndSchema(){

        IssueType bug = issueTypeSchemaService.createType(new IssueType( "Bug", "AlertTriangle"));
        IssueType epic = issueTypeSchemaService.createType(new IssueType( "Epic", "Zap"));
        IssueType improvement = issueTypeSchemaService.createType(new IssueType( "Improvement", "ArrowUp"));
        IssueType newFeature = issueTypeSchemaService.createType(new IssueType( "New Feature", "Plus"));
        IssueType story = issueTypeSchemaService.createType(new IssueType("Story", "FileText"));
        IssueType subTask = issueTypeSchemaService.createType(new IssueType( "Sub-task", "Copy"));
        IssueType task = issueTypeSchemaService.createType(new IssueType( "Task", "CheckSquare"));

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

        Project demoProject = new Project("DEMO", "Demo", "Project to demo features", wfl.getId(), schema.getId());
        projectService.create(demoProject);

    }
}