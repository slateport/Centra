package dev.conductor.dataservices.dto;

import dev.conductor.dataservices.entities.*;

import java.util.Date;
import java.util.List;

public class IssueDTO {

    private final String id;
    private final long externalId;
    private final String projectKey;
    private final String title;
    private final String description;
    private final Date createdDate;
    private final Date lastModifiedDate;
    private final String projectId;
    private final WorkflowState workflowState;
    private final String workflowId;
    private final String createdByUserId;
    private final String assigneeId;
    private final String lastModifiedByUserId;
    private final IssuePriority issuePriority;
    private final List<Label> labels;

    public IssueDTO(
            String id,
            long externalId,
            String projectKey,
            String title,
            String description,
            Date createdDate,
            Date lastModifiedDate,
            String projectId,
            WorkflowState workflowState,
            String workflowId,
            String createdByUserId,
            String assigneeId,
            String lastModifiedByUserId,
            IssuePriority issuePriority,
            List<Label> labels
    ) {
        this.id = id;
        this.externalId = externalId;
        this.projectKey = projectKey;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.projectId = projectId;
        this.workflowState = workflowState;
        this.workflowId = workflowId;
        this.createdByUserId = createdByUserId;
        this.assigneeId = assigneeId;
        this.lastModifiedByUserId = lastModifiedByUserId;
        this.issuePriority = issuePriority;
        this.labels = labels;
    }

    public static IssueDTO fromIssue (Issue issue, Project project) {
        return new IssueDTO(
            issue.getId(),
            issue.getExternalId(),
            project.getProjectKey(),
            issue.getTitle(),
            issue.getDescription(),
            issue.getCreatedDate(),
            issue.getLastModifiedDate(),
            project.getId(),
            issue.getWorkflowState(),
            issue.getWorkflowId(),
            issue.getCreatedByUserId(),
            issue.getAssigneeId(),
            issue.getLastModifiedByUserId(),
            issue.getIssuePriority(),
            issue.getLabels()
        );
    }

    public String getId() {
        return id;
    }

    public long getExternalId() {
        return externalId;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public String getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    public IssuePriority getIssuePriority() {
        return issuePriority;
    }

    public List<Label> getLabels() {
        return labels;
    }
}
