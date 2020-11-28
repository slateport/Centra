package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issue_priority")
public class IssuePriority {
    @Id
    private String id;
    @Indexed(unique=true)
    private final String label;
    private final String icon;

    public IssuePriority(String label, String icon) {
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
