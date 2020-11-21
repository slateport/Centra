package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {

    public static String DEFAULT_ISSUE_TYPE_SCHEMA_NAME = "Default Issue Type Schema";
    public static String DEFAULT_WORKFLOW_NAME = "Default Workflow";

    @Id private java.lang.String id;
    @Indexed(unique=true)
    private final String projectKey;
    private final String projectName;
    private final String description;
    private String workflowId;
    private String issueTypeSchemaId;

    public Project(String projectKey, String projectName, String description, String workflowId, String issueTypeSchemaId) {
        this.projectKey = projectKey;
        this.projectName = projectName;
        this.description = description;
        this.workflowId = workflowId;
        this.issueTypeSchemaId = issueTypeSchemaId;
        validateProjectKey(projectKey);
    }

    public java.lang.String getId() {
        return id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getIssueTypeSchemaId() {
        return issueTypeSchemaId;
    }

    public void setIssueTypeSchemaId(String issueTypeSchemaId) {
        this.issueTypeSchemaId = issueTypeSchemaId;
    }

    protected void validateProjectKey(String projectKey) {

    }
}
