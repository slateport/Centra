package dev.conductor.centra.dto;

public class UserLiteDTO {

    private final String id;
    private final String displayName;
    private final String username;

    public UserLiteDTO(String id, String displayName, String username) {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }
}
