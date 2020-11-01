package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Workflow;
import dev.conductor.dataservices.service.IssueService;
import dev.conductor.dataservices.service.IssueWorkflowService;
import dev.conductor.dataservices.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueWorkflowServiceImpl implements IssueWorkflowService {

    @Autowired
    private IssueService issueService;

    @Autowired
    private WorkflowService workflowService;

    @Override
    public Boolean workflowValidForIssue(Workflow workflow, Issue issue) {
        return workflow.getProjectId().equals(issue.getProjectId());
    }
}
