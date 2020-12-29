package dev.conductor.centra.domain.applicationUser.api;

import dev.conductor.centra.domain.applicationUser.dto.UserLiteDTO;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.applicationUser.entiity.UserGroup;
import dev.conductor.centra.domain.applicationUser.spi.ApplicationUserPersistencePort;
import dev.conductor.centra.domain.applicationUser.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserServiceAdapter implements ApplicationUserService {

    @Autowired
    private ApplicationUserPersistencePort persistence;

    @Override
    public ApplicationUser findByUsername(String username) {
        return persistence.findByUsername(username);
    }

    @Override
    public ApplicationUser findByEmailAddress(String emailAddress) {
        return persistence.findByEmailAddress(emailAddress);
    }

    @Override
    public ApplicationUser findById(String id) {
        return persistence.findById(id);
    }

    @Override
    public List<ApplicationUser> findAll() {
        return persistence.findAll();
    }

    @Override
    public ApplicationUser save(ApplicationUser user) {
        return persistence.save(user);
    }

    @Override
    public ApplicationUser createUser(ApplicationUser user) {

        if (findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Account with username already exists");
        }

        if (findByEmailAddress(user.getEmailAddress()) != null) {
            throw new UserAlreadyExistsException("Account with email already exists");
        }

        if (user.getUserGroups().isEmpty()){
            UserGroup group = findGroupByName(UserGroup.CENTRA_USERS);
            user.getUserGroups().add(group);
        }

        if (user.getAdmin() != null && user.getAdmin()) {
            user.getUserGroups().add(findGroupByName(UserGroup.CENTRA_ADMINISTRATORS));
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        persistence.save(user);

        return user;
    }

    @Override
    public Boolean isAdmin(ApplicationUser user) {
        if (user == null){
            return false;
        }

        // Legacy isAdmin flag
        if (user.getAdmin()) {
            return true;
        }


        return user.getUserGroups()
                .stream()
                .anyMatch(userGroup -> userGroup.getName().equals(UserGroup.CENTRA_ADMINISTRATORS));
    }

    @Override
    public void changePassword(ApplicationUser user, String password) {
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        save(user);
    }

    @Override
    public List<UserLiteDTO>
    findAllLite() {
        List<UserLiteDTO> results = new ArrayList<>();

        for (ApplicationUser user : findAll()) {
            results.add(new UserLiteDTO(user.getId(), user.getDisplayName(), user.getUsername(), isAdmin(user)));
        }

        return results;
    }

    @Override
    public UserGroup saveGroup(UserGroup group) {
        return persistence.saveGroup(group);
    }

    @Override
    public UserGroup findGroupById(String id) {
        return persistence.findGroupById(id);
    }

    @Override
    public UserGroup findGroupByName(String name) {
        return persistence.findGroupByName(name);
    }
}
