package dev.conductor.centra.infrastructure.persistence.mongodb.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "issue_priority_schemas")
public class IssuePrioritySchemaEntity {

    private String id;
    @Indexed(unique = true)
    private final String name;
    private List<String> priorityIds;

    public IssuePrioritySchemaEntity(String name) {
        this.name = name;
        this.priorityIds = new ArrayList<>();
    }

    @PersistenceConstructor
    public IssuePrioritySchemaEntity(String id, String name) {
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

    public void addPriority(IssuePriorityEntity priority) {
        this.priorityIds.add(priority.getId());
    }
}
