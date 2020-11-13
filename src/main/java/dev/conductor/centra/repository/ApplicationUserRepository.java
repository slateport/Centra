package dev.conductor.centra.repository;

import dev.conductor.centra.entities.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String>  {

    ApplicationUser findByUsername(String username);
}
