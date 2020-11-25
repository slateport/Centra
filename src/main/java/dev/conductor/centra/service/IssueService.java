package dev.conductor.centra.service;

import dev.conductor.centra.dto.IssueChangeDTO;
import dev.conductor.centra.entities.Issue;

import java.util.List;

public interface IssueService {

    List<Issue> findByProjectId(String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue save(Issue issue);
    long getNextExternalIdByProject(String projectId);
    List<IssueChangeDTO> getAuditLogsForIssue(Issue issue);
    void deleteIssue(Issue issue);
}
