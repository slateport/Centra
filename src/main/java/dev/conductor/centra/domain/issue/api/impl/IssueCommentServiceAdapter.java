package dev.conductor.centra.domain.issue.api.impl;

import dev.conductor.centra.domain.issue.api.IssueCommentService;
import dev.conductor.centra.domain.issue.entity.IssueComment;
import dev.conductor.centra.infrastructure.persistence.mongodb.IssueCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueCommentServiceAdapter implements IssueCommentService {

    @Autowired
    private IssueCommentRepository issueCommentRepository;

    @Override
    public List<IssueComment> findByIssueId(String issueId) {
        return issueCommentRepository.findByIssueIdOrderByCreatedDateAsc(issueId);
    }

    @Override
    public void save(IssueComment comment) {
        issueCommentRepository.save(comment);
    }
}
