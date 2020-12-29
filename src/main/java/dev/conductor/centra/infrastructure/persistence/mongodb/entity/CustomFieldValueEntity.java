package dev.conductor.centra.infrastructure.persistence.mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "custom_field_values")
public class CustomFieldValueEntity {

    @Id private String id;
    private String issueId;
    private String customFieldId;
    private String stringValue;
}
