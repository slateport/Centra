package dev.conductor.centra.domain.workflow.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Workflow {

    private String id;
    private String name;
    private List<WorkflowState> states;
    private List<WorkflowTransition> transitions;
    private Integer level;
    private List flow;

    public Workflow(){}

    public Workflow(String name, List<WorkflowState> states, List<WorkflowTransition> transitions, Integer level, List flow) {
        this.name = name;
        this.states = states;
        this.transitions = transitions;
        this.level = level;
        this.flow = flow;
    }
}
