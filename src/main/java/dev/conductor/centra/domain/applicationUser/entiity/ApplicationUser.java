package dev.conductor.centra.domain.applicationUser.entiity;

import lombok.Getter;
import lombok.Setter;

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
}
