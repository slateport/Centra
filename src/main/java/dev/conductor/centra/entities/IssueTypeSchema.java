package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Document(collection = "issue_type_schemas")
public class IssueTypeSchema {

    @Id
    private String id;
    @Indexed(unique = true)
    private final String name;
    private List<String> issueTypeIds;

    public IssueTypeSchema(String name){
        this.name = name;
        this.issueTypeIds = new ArrayList<>();
    }

    @PersistenceConstructor
    public IssueTypeSchema(String id, String name) {
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

    public void addIssueType(IssueType issueType) {
        this.issueTypeIds.add(issueType.getId());
    }
}
