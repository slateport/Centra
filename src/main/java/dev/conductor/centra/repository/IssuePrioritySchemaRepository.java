package dev.conductor.centra.repository;

import dev.conductor.centra.entities.IssuePrioritySchema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssuePrioritySchemaRepository extends MongoRepository<IssuePrioritySchema, String> {

    IssuePrioritySchema findByName(String name);
}
