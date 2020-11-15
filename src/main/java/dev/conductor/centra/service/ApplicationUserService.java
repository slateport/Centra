package dev.conductor.centra.service;

import dev.conductor.centra.dto.UserLiteDTO;
import dev.conductor.centra.entities.ApplicationUser;

import java.security.Principal;
import java.util.List;

public interface ApplicationUserService {

    List<ApplicationUser> findAll();
    ApplicationUser save(ApplicationUser user);
    public long count();
    ApplicationUser findByUsername(String username);
    ApplicationUser findById(String id);
    ApplicationUser createUser(ApplicationUser user);
    Boolean isAdmin(Principal principal);
    void changePassword(ApplicationUser user, String password);
    List<UserLiteDTO> findAllLite();
}
