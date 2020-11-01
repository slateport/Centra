package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.entities.IssueComment;
import dev.conductor.dataservices.repository.IssueCommentRepository;
import dev.conductor.dataservices.service.IssueCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueCommentServiceImpl implements IssueCommentService {

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
