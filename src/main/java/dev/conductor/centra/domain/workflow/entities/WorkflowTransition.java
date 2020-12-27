package dev.conductor.centra.domain.workflow.entities;

import java.util.Objects;

public class WorkflowTransition {

    private final String fromNode;
    private final String toNode;
    private final String label;
    private Boolean isInitial = false;
    private Boolean isTerminus = false;

    public WorkflowTransition(String fromNode, String toNode, String label, Boolean isInitial, Boolean isTerminus) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.label = label;
        this.isInitial = isInitial;
        this.isTerminus = isTerminus;
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

    public Boolean getTerminus() {
        return isTerminus;
    }

    public void setTerminus(Boolean terminus) {
        isTerminus = terminus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkflowTransition)) return false;
        WorkflowTransition that = (WorkflowTransition) o;
        return Objects.equals(fromNode, that.fromNode) &&
                Objects.equals(toNode, that.toNode) &&
                Objects.equals(isTerminus, that.isTerminus) &&
                Objects.equals(isInitial, that.isInitial) &&
                Objects.equals(label, that.label);
    }
}
