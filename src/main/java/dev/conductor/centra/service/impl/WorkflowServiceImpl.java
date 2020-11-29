package dev.conductor.centra.service.impl;

import dev.conductor.centra.entities.Issue;
import dev.conductor.centra.entities.Workflow;
import dev.conductor.centra.entities.WorkflowState;
import dev.conductor.centra.entities.WorkflowTransition;
import dev.conductor.centra.infrastructure.persistence.mongodb.WorkflowRepository;
import dev.conductor.centra.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowRepository repository;

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
    public WorkflowState transitionIssue(
            Issue issue, WorkflowTransition transition
    ) {
        Workflow workflow = findById(issue.getWorkflowId()).get();

        if(!getAvailableTransitions(workflow, issue.getWorkflowState()).contains(transition)){
            throw new RuntimeException("Transition not available or valid");
        }

        return workflow.getStates().stream()
                .filter(e -> e.getLabel().equals(transition.getToNode()))
                .findFirst()
                .orElse(new WorkflowState(true, false, "DEFUALT"));
    }
}
