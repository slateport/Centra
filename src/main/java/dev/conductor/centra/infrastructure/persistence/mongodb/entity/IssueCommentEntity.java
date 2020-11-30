package dev.conductor.centra.infrastructure.persistence.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "issue_comments")
public class IssueCommentEntity {

    @Id private String id;
    private final String issueId;
    private final String text;
    private final Date createdDate;
    private final String createdByUserId;

    @PersistenceConstructor
    public IssueCommentEntity(String id, String issueId, String text, Date createdDate, String createdByUserId) {
        this.id = id;
        this.issueId = issueId;
        this.text = text;
        this.createdDate = createdDate;
        this.createdByUserId = createdByUserId;
    }

    public IssueCommentEntity(String issueId, String text, Date createdDate, String createdByUserId) {
        this.issueId = issueId;
        this.text = text;
        this.createdDate = createdDate;
        this.createdByUserId = createdByUserId;
    }

    public String getId() {
        return id;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getText() {
        return text;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
