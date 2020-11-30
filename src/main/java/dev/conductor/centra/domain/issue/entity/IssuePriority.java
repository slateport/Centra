package dev.conductor.centra.domain.issue.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssuePriority {
    private String id;
    private String label;
    private String icon;

    public IssuePriority() {}

    public IssuePriority(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }
}
