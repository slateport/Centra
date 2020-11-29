package dev.conductor.centra.domain.issue.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issue_links")
public class IssueLinks {

    @Id
    private String id;

    @Indexed
    private String nodePublicId;

    @Indexed
    private String linkPublicId;

    public String getId() {
        return id;
    }

    public String getNodePublicId() {
        return nodePublicId;
    }

    public String getLinkPublicId() {
        return linkPublicId;
    }
}
