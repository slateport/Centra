package dev.conductor.centra.domain.issue.entity;

import dev.conductor.centra.domain.issue.dto.IssueDTO;
import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.DiffInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    public Issue() {}

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
}
