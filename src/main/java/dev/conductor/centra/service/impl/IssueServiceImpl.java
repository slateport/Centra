package dev.conductor.centra.service.impl;

import dev.conductor.centra.dto.IssueChangeDTO;
import dev.conductor.centra.entities.ApplicationUser;
import dev.conductor.centra.entities.Issue;
import dev.conductor.centra.repository.IssueRepository;
import dev.conductor.centra.service.ApplicationUserService;
import dev.conductor.centra.service.IssueService;
import org.javers.core.Changes;
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

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    ApplicationUserService userService;

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
        return null;
    }


}
