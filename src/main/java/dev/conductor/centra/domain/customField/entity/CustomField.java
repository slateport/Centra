package dev.conductor.centra.domain.customField.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomField {

    private String id;
    private String name;
    private Object defaultValue;
    private String valueType;
    private String projectId;
}
