package dev.conductor.centra.infrastructure.persistence.mongodb.entity;

import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.DiffInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "issues")
public class IssueEntity {

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

}
