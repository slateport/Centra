package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "settings")
public class Settings {

    @Id
    private String id = null;
    @Indexed(unique=true)
    private final String key;
    private final String value;

    public Settings(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @PersistenceConstructor
    public Settings(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
