package dev.conductor.centra.domain.issue.dto;

import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.workflow.entities.*;

import java.util.Date;
import java.util.List;

public class IssueDTO {

    private String id;
    private long externalId;
    private String projectKey;
    private String title;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
    private String projectId;
    private WorkflowState workflowState;
    private String workflowId;
    private String createdByUserId;
    private String assigneeId;
    private String lastModifiedByUserId;
    private String issuePriorityId;
    private String issueTypeId;
    private List<String> labels;

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
            String issuePriorityId,
            String issueTypeId, List<String> labels
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
        this.issuePriorityId = issuePriorityId;
        this.issueTypeId = issueTypeId;
        this.labels = labels;
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

    public String getIssuePriorityId() {
        return issuePriorityId;
    }

    public List<String> getLabels() {
        return labels;
    }

    public String getIssueTypeId() {
        return issueTypeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExternalId(long externalId) {
        this.externalId = externalId;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public void setLastModifiedByUserId(String lastModifiedByUserId) {
        this.lastModifiedByUserId = lastModifiedByUserId;
    }

    public void setIssuePriorityId(String issuePriorityId) {
        this.issuePriorityId = issuePriorityId;
    }

    public void setIssueTypeId(String issueTypeId) {
        this.issueTypeId = issueTypeId;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
