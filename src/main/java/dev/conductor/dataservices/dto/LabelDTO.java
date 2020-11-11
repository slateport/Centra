package dev.conductor.dataservices.dto;

public class LabelDTO {

    private final String externalId;
    private final String value;

    public LabelDTO(String id, String value) {
        this.externalId = id;
        this.value = value;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getValue() {
        return value;
    }
}
