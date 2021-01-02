package dev.conductor.centra.domain.workflow.entities;

import java.util.Objects;

public class WorkflowState {

    private final boolean isEntry;
    private final boolean isTerminus;
    private final String label;

    public WorkflowState(boolean isEntry, boolean isTerminus, String label) {
        this.isEntry = isEntry;
        this.isTerminus = isTerminus;
        this.label = label;
    }

    public boolean isEntry() {
        return isEntry;
    }
    public boolean getIsTerminus() {
        return isTerminus;
    }
    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowState that = (WorkflowState) o;
        return isEntry == that.isEntry && isTerminus == that.isTerminus && label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEntry, isTerminus, label);
    }
}
