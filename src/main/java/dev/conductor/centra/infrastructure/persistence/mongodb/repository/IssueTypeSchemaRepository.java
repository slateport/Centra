package dev.conductor.centra.infrastructure.persistence.mongodb.repository;

import dev.conductor.centra.domain.issue.entity.IssueTypeSchema;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IssueTypeSchemaRepository extends MongoRepository<IssueTypeSchema, String>  {

    IssueTypeSchema findByName(String name);
    Optional<IssueTypeSchema> findById(String id);
}
