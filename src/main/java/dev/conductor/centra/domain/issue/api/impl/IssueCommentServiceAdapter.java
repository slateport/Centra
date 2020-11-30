package dev.conductor.centra.domain.issue.api.impl;

import dev.conductor.centra.domain.issue.api.IssueCommentService;
import dev.conductor.centra.domain.issue.entity.IssueComment;
import dev.conductor.centra.infrastructure.persistence.mongodb.adapter.IssueCommentPersistenceAdapter;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.IssueCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueCommentServiceAdapter implements IssueCommentService {

    @Autowired
    private IssueCommentPersistenceAdapter persistenceAdapter;

    @Override
    public List<IssueComment> findByIssueId(String issueId) {
        return persistenceAdapter.findByIssueIdOrderByCreatedDateAsc(issueId);
    }

    @Override
    public void save(IssueComment comment) {
        persistenceAdapter.save(comment);
    }
}
