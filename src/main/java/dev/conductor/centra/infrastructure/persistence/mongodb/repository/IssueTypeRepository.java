package dev.conductor.centra.infrastructure.persistence.mongodb.repository;

import dev.conductor.centra.domain.issue.entity.IssueType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IssueTypeRepository extends MongoRepository<IssueType, String> {

    IssueType findByLabel(String label);
    Optional<IssueType> findById(String id);
}
