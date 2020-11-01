package dev.conductor.dataservices.entities;

import dev.conductor.dataservices.dto.IssueDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "issues")
public class Issue {

    public static final IssuePriority DEFAULT_PRIORITY = IssuePriority.MEDIUM;

    @Id private String id;
    private final long externalId;
    private final String projectId;
    private final String title;
    private final String description;
    private final Date createdDate;
    private final Date lastModifiedDate;
    private final String workflowId;
    private final WorkflowState workflowState;
    private final String createdByUserId;
    private final String lastModifiedByUserId;
    private final IssuePriority issuePriority;

    public Issue(
            long externalId,
            String title,
            String description,
            String projectId,
            Date createdDate,
            Date lastModifiedDate,
            WorkflowState workflowState,
            String workflowId,
            String createdByUserId,
            String lastModifiedByUserId,
            IssuePriority issuePriority) {
        this.externalId = externalId;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.workflowState = workflowState;
        this.workflowId = workflowId;
        this.createdByUserId = createdByUserId;
        this.lastModifiedByUserId = lastModifiedByUserId;
        this.issuePriority = issuePriority;
    }

    @PersistenceConstructor
    protected Issue(
            String id,
            long externalId,
            String title,
            String description,
            String projectId,
            Date createdDate,
            Date lastModifiedDate,
            WorkflowState workflowState,
            String workflowId,
            String createdByUserId,
            String lastModifiedByUserId,
            IssuePriority issuePriority) {
        this.id = id;
        this.externalId = externalId;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.workflowState = workflowState;
        this.workflowId = workflowId;
        this.createdByUserId = createdByUserId;
        this.lastModifiedByUserId = lastModifiedByUserId;
        this.issuePriority = issuePriority;
    }

    public static Issue fromIssueDto(IssueDTO issueDTO) {
        return new Issue(
            issueDTO.getId(),
            issueDTO.getExternalId(),
            issueDTO.getTitle(),
            issueDTO.getDescription(),
            issueDTO.getProjectId(),
            issueDTO.getCreatedDate(),
            issueDTO.getLastModifiedDate(),
            issueDTO.getWorkflowState(),
            issueDTO.getWorkflowId(),
            issueDTO.getLastModifiedByUserId(),
            issueDTO.getCreatedByUserId(),
            issueDTO.getIssuePriority());
    }

    public String getId() {
        return id;
    }

    public long getExternalId() {
        return externalId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public String getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    public IssuePriority getIssuePriority() {
        return issuePriority;
    }
}
