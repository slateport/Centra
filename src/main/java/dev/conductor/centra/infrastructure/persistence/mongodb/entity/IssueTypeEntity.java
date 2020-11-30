package dev.conductor.centra.infrastructure.persistence.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issue_types")
public class IssueTypeEntity {

    @Id private String id;
    @Indexed(unique=true)
    private final String label;
    private final String icon;

    public IssueTypeEntity(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getIcon() {
        return icon;
    }
}
