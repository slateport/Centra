package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.repository.IssueRepository;
import dev.conductor.dataservices.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

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
}
