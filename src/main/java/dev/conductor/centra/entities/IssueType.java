package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issue_types")
public class IssueType {

    @Id private String id;
    @Indexed(unique=true)
    private String issueTypeSchemaId;
    private final String label;

    public IssueType(String label, String issueTypeSchemaId) {
        this.label = label;
        this.issueTypeSchemaId = issueTypeSchemaId;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getIssueTypeSchemaId() {
        return issueTypeSchemaId;
    }
}
