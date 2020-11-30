package dev.conductor.centra.domain.issue.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IssueComment {

    private String id;
    private String issueId;
    private String text;
    private Date createdDate;
    private String createdByUserId;
}
