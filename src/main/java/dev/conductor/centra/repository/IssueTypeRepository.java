package dev.conductor.centra.repository;

import dev.conductor.centra.entities.IssueType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IssueTypeRepository extends MongoRepository<IssueType, String> {

    IssueType findByLabel(String label);
    Optional<IssueType> findById(String id);
}
