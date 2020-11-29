package dev.conductor.centra.domain.issue.entity;

import dev.conductor.centra.domain.issue.dto.IssueDTO;
import dev.conductor.centra.domain.workflow.entities.WorkflowState;
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
    @DiffInclude private long externalId;
    @DiffInclude private String projectId;
    @DiffInclude private String title;
    @DiffInclude private String description;
    @DiffInclude private Date createdDate;
    @DiffIgnore private Date lastModifiedDate;
    @DiffInclude private String workflowId;
    @DiffInclude private WorkflowState workflowState;
    @DiffInclude private String createdByUserId;
    @DiffInclude private String assigneeId;
    @DiffInclude private String lastModifiedByUserId;
    @DiffInclude private String issuePriorityId;
    @DiffInclude private String issueTypeId;
    @DiffInclude private List<String> labels;

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

    public void setExternalId(long externalId) {
        this.externalId = externalId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
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
