package dev.conductor.centra.infrastructure.persistence.mongodb.repository;

import dev.conductor.centra.domain.issue.entity.IssuePriority;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IssuePriorityRepository extends MongoRepository<IssuePriority, String> {

    IssuePriority findByLabel(String label);
    Optional<IssuePriority> findById(String id);
}
