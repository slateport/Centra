package dev.conductor.centra.domain.userAssociation.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAssociation {
    private String id;
    private String associationType;
    private String sinkNodeEntity;
    private String sourceId;
    private String sinkId;
}
