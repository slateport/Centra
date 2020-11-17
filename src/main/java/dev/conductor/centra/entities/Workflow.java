package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "workflows")
public class Workflow {

    @Id private String id;
    @Indexed(unique = true)
    private final String name;
    private final List<WorkflowState> states;
    private final List<WorkflowTransition> transitions;

    public Workflow(String name, List<WorkflowState> states, List<WorkflowTransition> transitions) {
        this.name = name;
        this.states = states;
        this.transitions = transitions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<WorkflowState> getStates() {
        return states;
    }

    public List<WorkflowTransition> getTransitions() {
        return transitions;
    }
}
