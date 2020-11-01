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

    public Project(String projectKey, String projectName) {
        this.projectKey = projectKey;
        this.projectName = projectName;
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

    protected void validateProjectKey(String projectKey) {

    }
}
