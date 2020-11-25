package dev.conductor.centra.service.impl;

import dev.conductor.centra.entities.Project;
import dev.conductor.centra.repository.ProjectRepository;
import dev.conductor.centra.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    IssueTypeSchemaService issueTypeSchemaService;

    @Autowired
    WorkflowService workflowService;

    @Autowired
    IssuePrioritySchemaService issuePrioritySchemaService;

    @Autowired
    IssueService issueService;

    @Override
    public Project findByKey(String key) {
        return projectRepository.findByProjectKey(key);
    }

    @Override
    public Project create(Project project) {

        if (project.getIssueTypeSchemaId() == null) {
            project.setIssueTypeSchemaId(
                issueTypeSchemaService.findSchemaByName(Project.DEFAULT_ISSUE_TYPE_SCHEMA_NAME).getId()
            );
        }

        if (project.getWorkflowId() == null) {
            project.setWorkflowId(
                workflowService.findByName(Project.DEFAULT_WORKFLOW_NAME).getId()
            );
        }

        if (project.getPrioritySchemaId() == null) {
            project.setPrioritySchemaId(
                    issuePrioritySchemaService.findSchemaByName(Project.DEFAULT_PRIORITY_SCHEMA_NAME).getId()
            );
        }

        return projectRepository.save(project);
    }

    @Override
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findByProjectName(name);
    }

    @Override
    public void delete(Project project) {
        issueService.findByProjectId(project.getId()).stream().map(
                issue -> {
                    issueService.deleteIssue(issue);
                    return issue;
                }
        );

        projectRepository.delete(project);
    }
}
