package dev.conductor.centra.service;

import dev.conductor.centra.entities.Issue;
import org.javers.core.Changes;

import java.util.List;

public interface IssueService {

    List<Issue> findByProjectId(String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue save(Issue issue);
    long getNextExternalIdByProject(String projectId);
    Changes getAuditLogsForIssue(Issue issue);
}
