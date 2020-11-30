package dev.conductor.centra.domain.issue.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IssueType {

    private String id;
    private String label;
    private String icon;

    public IssueType(){}

    public IssueType(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }
}
