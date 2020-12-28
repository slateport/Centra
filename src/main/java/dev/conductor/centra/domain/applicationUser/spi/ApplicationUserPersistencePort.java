package dev.conductor.centra.domain.applicationUser.spi;

import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.applicationUser.entiity.UserGroup;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserPersistencePort {

    ApplicationUser findByUsername(String username);
    List<ApplicationUser> findAll();
    ApplicationUser findByEmailAddress(String emailAddress);
    ApplicationUser findById(String id);
    ApplicationUser save(ApplicationUser user);
    UserGroup findGroupById(String id);
    UserGroup saveGroup(UserGroup group);
    UserGroup findGroupByName(String name);
}
