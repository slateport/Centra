package dev.conductor.centra.application.adapter;

import dev.conductor.centra.application.api.IssueService;
import dev.conductor.centra.domain.issue.dto.IssueChangeDTO;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.issue.entity.IssueLinks;
import dev.conductor.centra.infrastructure.persistence.mongodb.IssueLinksRepository;
import dev.conductor.centra.infrastructure.persistence.mongodb.IssueRepository;
import dev.conductor.centra.application.api.ApplicationUserService;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceAdapter implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    ApplicationUserService userService;

    @Autowired
    IssueLinksRepository issueLinksRepository;

    @Autowired
    private Javers javers;

    @Override
    public List<Issue> findByProjectId(String project) {
        return issueRepository.findByProjectId(project);
    }

    @Override
    public Issue findByProjectIdAndExternalId(String projectId, long externalId) {
        return issueRepository.findByProjectIdAndExternalId(projectId, externalId);
    }

    @Override
    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public synchronized long getNextExternalIdByProject(String projectId) {
        Issue issue = issueRepository.findFirstByProjectIdOrderByCreatedDateDesc(projectId);
        if (issue == null) {
            return 1;
        } else {
            return issue.getExternalId()+1;
        }
    }

    public List<IssueChangeDTO> getAuditLogsForIssue(Issue issue) {
        List<IssueChangeDTO> results = new ArrayList<>();

        javers.findChanges(
            QueryBuilder
                    .byInstance(issue)
                    .withChildValueObjects()
                    .build()
        ).forEach(change -> {
            ApplicationUser author = userService
                    .findByUsername(change.getCommitMetadata().get().getAuthor());

            results.add(new IssueChangeDTO(
                    author.getId(),
                    change.getCommitMetadata().get().getCommitDate(),
                    getPropertyNameWithPath(change),
                    getRight(change),
                    getLeft(change)

            ));
        });

        return results;
    }

    @Override
    public void deleteIssue(Issue issue) {
        issueRepository.delete(issue);
    }

    @Override
    public IssueLinks saveIssueLinks(IssueLinks issueLinks) {
        return issueLinksRepository.save(issueLinks);
    }

    @Override
    public List<IssueLinks> getLinksForIssueByExternalId(String externalId) {
        List<IssueLinks> links = issueLinksRepository.findByLinkPublicId(externalId);
        links.addAll(issueLinksRepository.findByNodePublicId(externalId));

        return links;
    }

    @Override
    public IssueLinks findLinkById(String id) {
        Optional<IssueLinks> optionalLink = issueLinksRepository.findById(id);
        return (optionalLink.isEmpty()) ? null : optionalLink.get();
    }

    @Override
    public void deleteIssueLink(IssueLinks issueLinks) {
        issueLinksRepository.delete(issueLinks);
    }

    private String getPropertyNameWithPath(Change change) {
        if (change instanceof PropertyChange) {
            return ((PropertyChange) change).getPropertyNameWithPath().replace(".", ":");
        }

        if (change instanceof ValueChange) {
            return ((ValueChange) change).getPropertyNameWithPath().replace(".", ":");
        }

        return "";
    }

    private Object getRight(Change change) {
        if (change instanceof ValueChange){
            return ((ValueChange) change).getRight();
        }

        if (change instanceof ListChange) {
            ContainerElementChange ce = ((ListChange) change).getChanges().get(0);

            if (ce instanceof ValueAdded) {
                return "Added: " + ((ValueAdded) ce).getValue();
            } else if (ce instanceof ValueRemoved) {
                return "Removed: " + ((ValueRemoved) ce).getValue();
            } else if (ce instanceof ElementValueChange) {
                return "Changed: " + ((ElementValueChange) ce).getRightValue();
            }
        }

        return null;
    }

    private Object getLeft(Change change) {
        if (change instanceof ValueChange){
            return ((ValueChange) change).getLeft();
        }

        if (change instanceof ListChange) {
            ContainerElementChange ce = ((ListChange) change).getChanges().get(0);

            if (ce instanceof ValueAdded) {
                return "Added: " + ((ValueAdded) ce).getValue();
            } else if (ce instanceof ValueRemoved) {
                return "Removed: " + ((ValueRemoved) ce).getValue();
            } else if (ce instanceof ElementValueChange) {
                return "Changed: " + ((ElementValueChange) ce).getLeftValue();
            }
        }

        return null;
    }
}
