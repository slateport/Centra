package dev.conductor.centra.service.impl;

import dev.conductor.centra.entities.Issue;
import dev.conductor.centra.repository.IssueRepository;
import dev.conductor.centra.service.IssueService;
import org.javers.core.Changes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private Javers javers;

    @Override
    public List<Issue> findByProjectId(String project) {
        return issueRepository.findByProjectId(project);
    }

    @Override
    public Issue findByProjectIdAndExternalId(String projectId, long externalId) {
        return issueRepository.findByProjectIdAndExternalId(projectId, externalId);
    }

    @Override
    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public synchronized long getNextExternalIdByProject(String projectId) {
        Issue issue = issueRepository.findFirstByProjectIdOrderByCreatedDateDesc(projectId);
        if (issue == null) {
            return 1;
        } else {
            return issue.getExternalId()+1;
        }
    }

    public Changes getAuditLogsForIssue(Issue issue) {
        QueryBuilder queryBuilder = QueryBuilder
                .byInstance(issue)
                .withSnapshotTypeUpdate();

        return javers.findChanges(queryBuilder.build());
    }
}
