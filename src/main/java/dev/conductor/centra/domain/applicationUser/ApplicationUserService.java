package dev.conductor.centra.domain.applicationUser;

import dev.conductor.centra.domain.applicationUser.dto.UserLiteDTO;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;

import java.security.Principal;
import java.util.List;

public interface ApplicationUserService {

    List<ApplicationUser> findAll();
    ApplicationUser save(ApplicationUser user);
    public long count();
    ApplicationUser findByUsername(String username);
    ApplicationUser findByEmailAddress(String emailAddress);
    ApplicationUser findById(String id);
    ApplicationUser createUser(ApplicationUser user);
    Boolean isAdmin(Principal principal);
    void changePassword(ApplicationUser user, String password);
    List<UserLiteDTO> findAllLite();
}
