package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.Issue;
import org.javers.core.Changes;
import org.javers.core.metamodel.object.CdoSnapshot;

import java.util.List;

public interface IssueService {

    List<Issue> findByProjectId(String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue save(Issue issue);
    long getNextExternalIdByProject(String projectId);
    Changes getAuditLogsForIssue(Issue issue);
}
