package dev.conductor.centra.application.api;

import dev.conductor.centra.domain.issue.dto.IssueChangeDTO;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.issue.entity.IssueLinks;

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
    IssueLinks findLinkById(String id);
    void deleteIssueLink(IssueLinks issueLinks);
}
