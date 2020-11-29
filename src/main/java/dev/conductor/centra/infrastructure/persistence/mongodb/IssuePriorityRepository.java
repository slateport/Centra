package dev.conductor.centra.infrastructure.persistence.mongodb;

import dev.conductor.centra.entities.IssuePriority;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IssuePriorityRepository extends MongoRepository<IssuePriority, String> {

    IssuePriority findByLabel(String label);
    Optional<IssuePriority> findById(String id);
}
