package dev.conductor.centra.domain.workflow.api;

import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.workflow.entities.Workflow;
import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import dev.conductor.centra.domain.workflow.entities.WorkflowTransition;

import java.util.List;
import java.util.Optional;

public interface WorkflowService {

    List<Workflow> findAll();
    Workflow findById(String id);
    Workflow findByName(String name);
    Workflow create(Workflow workflow);
    WorkflowState getInitialState(Workflow workflow);
    List<WorkflowTransition> getAvailableTransitions(Workflow workflow, WorkflowState currentState);
    Issue transitionIssue(Issue issue, WorkflowTransition transition, ApplicationUser user);
}
