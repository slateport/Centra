package dev.conductor.centra.domain.workflow.entities;

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
}
