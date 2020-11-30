package dev.conductor.centra.domain.workflow.entities;

import java.util.Objects;

public class WorkflowTransition {

    private final String fromNode;
    private final String toNode;
    private final String label;
    private Boolean isInitial = false;

    public WorkflowTransition(String fromNode, String toNode, String label, Boolean isInitial) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.label = label;
        this.isInitial = isInitial;
    }

    public String getFromNode() {
        return fromNode;
    }

    public String getToNode() {
        return toNode;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getInitial() {
        return isInitial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkflowTransition)) return false;
        WorkflowTransition that = (WorkflowTransition) o;
        return Objects.equals(fromNode, that.fromNode) &&
                Objects.equals(toNode, that.toNode) &&
                Objects.equals(label, that.label);
    }
}
