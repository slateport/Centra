package dev.conductor.centra.domain.issue.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "issue_priority_schemas")
public class IssuePrioritySchema {

    private String id;
    @Indexed(unique = true)
    private final String name;
    private List<String> priorityIds;

    public IssuePrioritySchema(String name) {
        this.name = name;
        this.priorityIds = new ArrayList<>();
    }

    @PersistenceConstructor
    public IssuePrioritySchema(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPriorityIds() {
        return priorityIds;
    }

    public void addPriority(IssuePriority priority) {
        this.priorityIds.add(priority.getId());
    }
}
