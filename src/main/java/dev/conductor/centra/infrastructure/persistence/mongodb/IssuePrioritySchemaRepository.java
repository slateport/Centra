package dev.conductor.centra.infrastructure.persistence.mongodb;

import dev.conductor.centra.domain.issue.entity.IssuePrioritySchema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssuePrioritySchemaRepository extends MongoRepository<IssuePrioritySchema, String> {

    IssuePrioritySchema findByName(String name);
}
