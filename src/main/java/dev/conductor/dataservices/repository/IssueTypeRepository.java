package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.IssueType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IssueTypeRepository extends MongoRepository<IssueType, String> {

    IssueType findByLabel(String label);
    Optional<IssueType> findById(String id);
}
