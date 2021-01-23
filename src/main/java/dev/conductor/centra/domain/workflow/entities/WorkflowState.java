package dev.conductor.centra.domain.workflow.entities;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class WorkflowState {

    private final boolean isEntry;
    private final boolean isTerminus;
    private final String label;
    private final Integer order;
    private List<StatePort> ports;

    public WorkflowState(boolean isEntry, boolean isTerminus, String label, Integer order, List<StatePort> ports) {
        this.isEntry = isEntry;
        this.isTerminus = isTerminus;
        this.label = label;
        this.order = order;
        this.ports = ports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowState that = (WorkflowState) o;
        return isEntry == that.isEntry && isTerminus == that.isTerminus && label.equals(that.label) && order.equals(that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEntry, isTerminus, label);
    }
}
