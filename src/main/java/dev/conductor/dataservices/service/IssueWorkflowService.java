package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Workflow;

public interface IssueWorkflowService {

    Boolean workflowValidForIssue(Workflow workflow, Issue issue);
}
