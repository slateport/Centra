package dev.conductor.centra.infrastructure.persistence.mongodb;

import dev.conductor.centra.entities.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String>  {

    ApplicationUser findByUsername(String username);
    ApplicationUser findByEmailAddress(String emailAddress);
}
