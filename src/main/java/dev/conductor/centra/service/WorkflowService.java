package dev.conductor.centra.service;

import dev.conductor.centra.entities.Issue;
import dev.conductor.centra.entities.Workflow;
import dev.conductor.centra.entities.WorkflowState;
import dev.conductor.centra.entities.WorkflowTransition;

import java.util.List;
import java.util.Optional;

public interface WorkflowService {

    List<Workflow> findAll();
    Optional<Workflow> findById(String id);
    void save(Workflow workflow);
    WorkflowState getInitialState(Workflow workflow);
    List<WorkflowTransition> getAvailableTransitions(Workflow workflow, WorkflowState currentState);
    WorkflowState transitionIssue(Issue issue, WorkflowTransition transition);
}
