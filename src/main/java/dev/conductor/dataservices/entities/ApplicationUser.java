package dev.conductor.dataservices.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users")
public class ApplicationUser implements Serializable {

    @Id private String id;
    private String username;
    private String emailAddress;
    private String password;
    private String displayName;
    private Boolean admin;
    private Boolean enabled;

    public ApplicationUser(String username, String emailAddress, String password, String displayName, Boolean admin, Boolean enabled) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.displayName = displayName;
        this.admin = admin;
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
