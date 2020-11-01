package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Project;

import java.util.List;

public interface IssueService {

    List<Issue> findByProjectId(String projectId);
    Issue findByProjectIdAndExternalId(String projectId, long externalId);
    Issue save(Issue issue);
    long getNextExternalIdByProject(String projectId);
}
