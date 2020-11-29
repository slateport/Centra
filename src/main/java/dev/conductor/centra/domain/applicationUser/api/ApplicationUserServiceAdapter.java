package dev.conductor.centra.domain.applicationUser.api;

import dev.conductor.centra.domain.applicationUser.dto.UserLiteDTO;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
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
        Optional<ApplicationUser> optionalApplicationUser = persistence.findById(id);
        return optionalApplicationUser.isEmpty() ? null : optionalApplicationUser.get();
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

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        persistence.save(user);

        return user;
    }

    @Override
    public Boolean isAdmin(Principal principal) {
        if (principal == null){
            return false;
        }

        ApplicationUser user = findByUsername(principal.getName());

        return user != null && user.getAdmin();
    }

    @Override
    public void changePassword(ApplicationUser user, String password) {
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        save(user);
    }

    @Override
    public List<UserLiteDTO> findAllLite() {
        List<UserLiteDTO> results = new ArrayList<>();

        for (ApplicationUser user : findAll()) {
            results.add(new UserLiteDTO(user.getId(), user.getDisplayName(), user.getUsername(), user.getAdmin()));
        }

        return results;
    }
}
