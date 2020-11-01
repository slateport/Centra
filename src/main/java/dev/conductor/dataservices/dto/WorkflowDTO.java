package dev.conductor.dataservices.dto;

import dev.conductor.dataservices.entities.WorkflowState;
import dev.conductor.dataservices.entities.WorkflowTransition;

import java.util.List;

public class WorkflowDTO {

    private String projectId;
    private String issueTypeId;
    private String name;
    private String id;
    private List<WorkflowState> states;
    private List<WorkflowTransition> transitions;

    public String getProjectId() {
        return projectId;
    }

    public String getIssueTypeId() {
        return issueTypeId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<WorkflowState> getStates() {
        return states;
    }

    public List<WorkflowTransition> getTransitions() {
        return transitions;
    }
}
