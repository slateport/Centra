package dev.conductor.centra.domain.applicationUser.entiity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroup {

    public static final String CENTRA_USERS = "centra-users";
    public static final String CENTRA_ADMINISTRATORS = "centra-administrators";

    private String id;
    private String name;
}
