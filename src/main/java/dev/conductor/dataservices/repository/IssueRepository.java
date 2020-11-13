package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.Issue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueRepository extends MongoRepository<Issue, String> {

    List<Issue> findByProjectId (String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue findFirstByProjectIdOrderByLastModifiedDateDesc(String projectId);
    Issue findFirstByProjectIdOrderByCreatedDateDesc(String projectId);
}
