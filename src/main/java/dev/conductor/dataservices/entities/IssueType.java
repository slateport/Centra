package dev.conductor.dataservices.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issueTypes")
public class IssueType {

    @Id private String id;
    @Indexed(unique=true)
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
