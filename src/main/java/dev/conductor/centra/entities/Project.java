package dev.conductor.centra.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {

    public static String DEFAULT_ISSUE_TYPE_SCHEMA_NAME = "Default Issue Type Schema";
    public static String DEFAULT_WORKFLOW_NAME = "Default Workflow";
    public static String DEFAULT_PRIORITY_SCHEMA_NAME = "Default Priority Schema";

    @Id private java.lang.String id;
    @Indexed(unique=true)
    private final String projectKey;
    private final String projectName;
    private final String description;
    private String workflowId;
    private String issueTypeSchemaId;
    private String prioritySchemaId;

    public Project(String projectKey, String projectName, String description, String workflowId, String issueTypeSchemaId, String prioritySchemaId) {
        this.projectKey = projectKey;
        this.projectName = projectName;
        this.description = description;
        this.workflowId = workflowId;
        this.issueTypeSchemaId = issueTypeSchemaId;
        this.prioritySchemaId = prioritySchemaId;
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

    public String getPrioritySchemaId() {
        return prioritySchemaId;
    }

    public void setPrioritySchemaId(String prioritySchemaId) {
        this.prioritySchemaId = prioritySchemaId;
    }

    protected void validateProjectKey(String projectKey) {

    }
}
