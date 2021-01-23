package dev.conductor.centra.domain.workflow.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatePort {
    private final String portType;
    private final String portPos;
    private final Integer target;
    private final Integer from;

    public StatePort(String portType, String portPos, Integer from, Integer target) {
        this.portType = portType;
        this.portPos = portPos;
        this.from = from;
        this.target = target;
    }
}
