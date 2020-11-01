package dev.conductor.dataservices.entities;

import org.springframework.data.annotation.Id;

public class IssueType {

    @Id private String id;
    private final String label;

    public IssueType(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
