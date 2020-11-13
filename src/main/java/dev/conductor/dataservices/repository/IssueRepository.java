package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.Issue;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@JaversSpringDataAuditable
public interface IssueRepository extends MongoRepository<Issue, String> {

    List<Issue> findByProjectId (String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue findFirstByProjectIdOrderByLastModifiedDateDesc(String projectId);
    Issue findFirstByProjectIdOrderByCreatedDateDesc(String projectId);
}
