package dev.conductor.centra.infrastructure.persistence.mongodb;

import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.applicationUser.spi.ApplicationUserPersistencePort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String>, ApplicationUserPersistencePort {
}
