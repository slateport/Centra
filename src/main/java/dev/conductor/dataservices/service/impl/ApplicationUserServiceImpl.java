package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.entities.ApplicationUser;
import dev.conductor.dataservices.repository.ApplicationUserRepository;
import dev.conductor.dataservices.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    @Autowired
    private ApplicationUserRepository repository;

    @Override
    public ApplicationUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<ApplicationUser> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public ApplicationUser createUser(ApplicationUser user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        repository.save(user);

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
}
