package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Workflow;
import dev.conductor.dataservices.entities.WorkflowState;
import dev.conductor.dataservices.entities.WorkflowTransition;

import java.util.List;
import java.util.Optional;

public interface WorkflowService {

    List<Workflow> findAll();
    Optional<Workflow> findById(String id);
    List<Workflow> findByProjectIdAndIssueTypeId(String projectId, String issueTypeId);
    void save(Workflow workflow);
    WorkflowState getInitialState(Workflow workflow);
    List<WorkflowTransition> getAvailableTransitions(Workflow workflow, WorkflowState currentState);
    WorkflowState transitionIssue(Issue issue, WorkflowTransition transition);
}
