package dev.conductor.centra.service;

import dev.conductor.centra.dto.IssueChangeDTO;
import dev.conductor.centra.entities.Issue;
import dev.conductor.centra.entities.IssueLinks;

import java.util.List;

public interface IssueService {

    List<Issue> findByProjectId(String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue save(Issue issue);
    long getNextExternalIdByProject(String projectId);
    List<IssueChangeDTO> getAuditLogsForIssue(Issue issue);
    void deleteIssue(Issue issue);
    IssueLinks saveIssueLinks(IssueLinks issueLinks);
    List<IssueLinks> getLinksForIssueByExternalId(String externalId);
}
