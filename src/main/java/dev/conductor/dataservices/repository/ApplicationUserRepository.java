package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String>  {

    ApplicationUser findByUsername(String username);
}
