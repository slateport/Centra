package dev.conductor.dataservices.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {

    @Id private java.lang.String id;
    @Indexed(unique=true)
    private final String projectKey;
    private final String projectName;
    private final String workflowId;

    public Project(String projectKey, String projectName, String workflowId) {
        this.projectKey = projectKey;
        this.projectName = projectName;
        this.workflowId = workflowId;
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

    public String getWorkflowId() {
        return workflowId;
    }

    protected void validateProjectKey(String projectKey) {

    }
}
