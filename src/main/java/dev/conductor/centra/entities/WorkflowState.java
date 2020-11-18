package dev.conductor.centra.entities;

public class WorkflowState {

    private boolean isEntry;
    private boolean isTerminus;
    private String label;

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
