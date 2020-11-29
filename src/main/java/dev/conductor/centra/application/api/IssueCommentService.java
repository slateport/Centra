package dev.conductor.centra.application.api;

import dev.conductor.centra.domain.issue.entity.IssueComment;

import java.util.List;

public interface IssueCommentService {

    List<IssueComment> findByIssueId(String issueId);
    void save (IssueComment comment);
}
