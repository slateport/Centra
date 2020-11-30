package dev.conductor.centra.domain.applicationUser.entiity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String emailAddress;
    private String password;
    private String displayName;
    private Boolean admin;
    private Boolean enabled;
}
