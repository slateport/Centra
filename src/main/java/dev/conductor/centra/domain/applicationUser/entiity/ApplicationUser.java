package dev.conductor.centra.domain.applicationUser.entiity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ApplicationUser {

    private String id;
    private String username;
    private String emailAddress;
    private String password;
    private String displayName;
    private Boolean admin;
    private Boolean enabled;
    private Set<UserGroup> userGroups = new HashSet<>();
}
