package dev.conductor.centra.domain.workflow.api.impl;

import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.issue.api.IssueService;
import dev.conductor.centra.domain.workflow.api.WorkflowService;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.workflow.entities.Workflow;
import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import dev.conductor.centra.domain.workflow.entities.WorkflowTransition;
import dev.conductor.centra.infrastructure.persistence.mongodb.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkflowServiceAdapter implements WorkflowService {

    @Autowired
    private WorkflowRepository repository;

    @Autowired
    private IssueService issueService;

    @Override
    public List<Workflow> findAll() {
        return repository.findAll();
    }

    public void save(Workflow workflow) {
        repository.save(workflow);
    }

    @Override
    public Optional<Workflow> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Workflow findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public WorkflowState getInitialState(Workflow workflow) {
        return workflow.getStates().stream()
                .filter(WorkflowState::isEntry)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Initial state could not be found for workflow"));
    }

    public List<WorkflowTransition> getAvailableTransitions(Workflow workflow, WorkflowState currentState) {
        if (currentState.getIsTerminus()){
            return new ArrayList<>();
        }

        return workflow.getTransitions().stream()
                .filter(e -> e.getFromNode().equals(currentState.getLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public Issue transitionIssue(Issue issue, WorkflowTransition transition, ApplicationUser user) {
        Optional<Workflow> workflowOptional = findById(issue.getWorkflowId());

        if (workflowOptional.isEmpty()) {
            throw new RuntimeException("Workflow doesn't exist");
        }

        Workflow workflow = workflowOptional.get();

        if(!getAvailableTransitions(workflow, issue.getWorkflowState()).contains(transition)){
            throw new RuntimeException("Transition not available or valid");
        }

        WorkflowState state = workflow.getStates().stream()
                .filter(e -> e.getLabel().equals(transition.getToNode()))
                .findFirst()
                .orElse(new WorkflowState(true, false, "DEFUALT"));

        issue.setWorkflowState(state);
        issue.setLastModifiedDate(new Date());
        issue.setLastModifiedByUserId(user.getId());

        issueService.save(issue);

        return issue;
    }
}
