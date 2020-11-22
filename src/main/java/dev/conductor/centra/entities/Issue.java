package dev.conductor.centra.entities;

import dev.conductor.centra.dto.IssueDTO;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.DiffInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "issues")
public class Issue {

    @Id private String id;
    @DiffInclude private final long externalId;
    @DiffInclude private final String projectId;
    @DiffInclude private final String title;
    @DiffInclude private final String description;
    @DiffInclude private final Date createdDate;
    @DiffIgnore private final Date lastModifiedDate;
    @DiffInclude private final String workflowId;
    @DiffInclude private final WorkflowState workflowState;
    @DiffInclude private final String createdByUserId;
    @DiffInclude private final String assigneeId;
    @DiffInclude private final String lastModifiedByUserId;
    @DiffInclude private String issuePriorityId;
    @DiffInclude private String issueTypeId;
    @DiffInclude private final List<String> labels;

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
            String assigneeId,
            String lastModifiedByUserId,
            String issuePriorityId,
            String issueTypeId, List<String> labels
    ) {
        this.externalId = externalId;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.workflowState = workflowState;
        this.workflowId = workflowId;
        this.createdByUserId = createdByUserId;
        this.assigneeId = assigneeId;
        this.lastModifiedByUserId = lastModifiedByUserId;
        this.issuePriorityId = issuePriorityId;
        this.issueTypeId = issueTypeId;
        this.labels = labels;
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
            String assigneeId,
            String lastModifiedByUserId,
            String issuePriorityId,
            String issueTypeId,
            List<String> labels
    ) {
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
        this.assigneeId = assigneeId;
        this.lastModifiedByUserId = lastModifiedByUserId;
        this.issuePriorityId = issuePriorityId;
        this.issueTypeId = issueTypeId;
        this.labels = labels;
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
            issueDTO.getAssigneeId(),
            issueDTO.getCreatedByUserId(),
            issueDTO.getIssuePriorityId(),
            issueDTO.getIssueTypeId(),
            issueDTO.getLabels()
        );
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
}
