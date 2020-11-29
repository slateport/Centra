package dev.conductor.centra.domain.workflow;

import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.workflow.entities.Workflow;
import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import dev.conductor.centra.domain.workflow.entities.WorkflowTransition;

import java.util.List;
import java.util.Optional;

public interface WorkflowService {

    List<Workflow> findAll();
    Optional<Workflow> findById(String id);
    Workflow findByName(String name);
    void save(Workflow workflow);
    WorkflowState getInitialState(Workflow workflow);
    List<WorkflowTransition> getAvailableTransitions(Workflow workflow, WorkflowState currentState);
    WorkflowState transitionIssue(Issue issue, WorkflowTransition transition);
}
