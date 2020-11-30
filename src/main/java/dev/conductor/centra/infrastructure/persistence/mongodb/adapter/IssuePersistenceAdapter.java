package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.issue.spi.IssuePersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssuePersistenceAdapter implements IssuePersistencePort {

    @Autowired
    IssueRepository repository;

    @Override
    public List<Issue> findByProjectId(String projectId) {
        return repository.findByProjectId(projectId);
    }

    @Override
    public Issue findByProjectIdAndExternalId(String projectId, long externalId) {
        return repository.findByProjectIdAndExternalId(projectId, externalId);
    }

    @Override
    public Issue findFirstByProjectIdOrderByLastModifiedDateDesc(String projectId) {
        return repository.findFirstByProjectIdOrderByLastModifiedDateDesc(projectId);
    }

    @Override
    public Issue findFirstByProjectIdOrderByCreatedDateDesc(String projectId) {
        return repository.findFirstByProjectIdOrderByCreatedDateDesc(projectId);
    }

    @Override
    public Issue save(Issue issue) {
        return repository.save(issue);
    }

    @Override
    public void delete(Issue issue) {
        repository.delete(issue);
    }
}
