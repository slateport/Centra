package dev.conductor.centra.service;

import dev.conductor.centra.entities.IssueComment;

import java.util.List;

public interface IssueCommentService {

    List<IssueComment> findByIssueId(String issueId);
    void save (IssueComment comment);
}
