package dev.conductor.centra.domain.workflow.dto;

import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import dev.conductor.centra.domain.workflow.entities.WorkflowTransition;

import java.util.List;

public class WorkflowDTO {

    private String projectId;
    private String name;
    private String id;
    private List<WorkflowState> states;
    private List<WorkflowTransition> transitions;

    public String getProjectId() {
        return projectId;
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
