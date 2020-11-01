package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.ApplicationUser;

import java.security.Principal;
import java.util.List;

public interface ApplicationUserService {

    List<ApplicationUser> findAll();
    public long count();
    ApplicationUser findByUsername(String username);
    ApplicationUser createUser(ApplicationUser user);
    Boolean isAdmin(Principal principal);
}
