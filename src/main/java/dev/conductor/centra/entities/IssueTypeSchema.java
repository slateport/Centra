package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

public class IssueTypeSchema {

    @Id
    private String id;
    @Indexed(unique = true)
    private final String name;

    public IssueTypeSchema(String name){
        this.name = name;
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
}
