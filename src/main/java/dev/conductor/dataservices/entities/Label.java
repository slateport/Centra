package dev.conductor.dataservices.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "labels")
public class Label {

    @Id private String id;
    @Indexed(unique=true)
    private String value;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
