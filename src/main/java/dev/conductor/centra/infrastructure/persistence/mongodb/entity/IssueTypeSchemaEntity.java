package dev.conductor.centra.infrastructure.persistence.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "issue_type_schemas")
public class IssueTypeSchemaEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private final String name;
    private List<String> issueTypeIds;

    public IssueTypeSchemaEntity(String name){
        this.name = name;
        this.issueTypeIds = new ArrayList<>();
    }

    @PersistenceConstructor
    public IssueTypeSchemaEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getIssueTypeIds() {
        return issueTypeIds;
    }

    public void addIssueType(IssueTypeEntity issueType) {
        this.issueTypeIds.add(issueType.getId());
    }
}
