package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.IssueComment;

import java.util.List;

public interface IssueCommentService {

    List<IssueComment> findByIssueId(String issueId);
    void save (IssueComment comment);
}
