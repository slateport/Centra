package dev.conductor.centra.application.controllers;

import dev.conductor.centra.domain.applicationUser.api.ApplicationUserService;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.issue.api.IssueCommentService;
import dev.conductor.centra.domain.issue.api.IssueService;
import dev.conductor.centra.domain.issue.api.IssueTypeSchemaService;
import dev.conductor.centra.domain.issue.dto.IssueChangeDTO;
import dev.conductor.centra.domain.issue.dto.IssueCommentDTO;
import dev.conductor.centra.domain.issue.dto.IssueDTO;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.issue.entity.IssueComment;
import dev.conductor.centra.domain.issue.entity.IssueLinks;
import dev.conductor.centra.domain.issue.entity.IssueType;
import dev.conductor.centra.domain.label.api.LabelService;
import dev.conductor.centra.domain.project.api.ProjectService;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.workflow.api.WorkflowService;
import dev.conductor.centra.domain.workflow.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final String PROJECT_NOT_FOUND_ERROR_MESSAGE = "Project not found";

    @Autowired
    private IssueService issueService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IssueCommentService issueCommentService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private IssueTypeSchemaService issueTypeSchemaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public IssueDTO findById(@PathVariable String id) {
        Issue issue = getIssueByExternalId(id);
        Optional<Project> project = projectService.findById(issue.getProjectId());

        if (project.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    PROJECT_NOT_FOUND_ERROR_MESSAGE
            );
        }
        return convertToDTO(issue);
    }

    @PostMapping
    public IssueDTO createIssue(@RequestBody IssueDTO issue, Principal principal) {
        Project project = projectService.findByKey(issue.getProjectKey());

        if (project == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,PROJECT_NOT_FOUND_ERROR_MESSAGE);
        }

        ApplicationUser user = applicationUserService.findByUsername(principal.getName());

        try {
            return convertToDTO(issueService.createIssue(issue, user));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    e.getMessage()
            );
        }
    }

    @PutMapping("/{id}")
    public IssueDTO updateIssue (@RequestBody IssueDTO issueDto, @PathVariable String id) {
        Issue issue = getIssueByExternalId(id);
        Optional<Project> project = projectService.findById(issue.getProjectId());

        if (project.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    PROJECT_NOT_FOUND_ERROR_MESSAGE
            );
        }

        Issue entityToSave = Issue.fromIssueDto(issueDto);
        issueService.save(entityToSave);

        return convertToDTO(entityToSave);
    }

    @GetMapping(value = "/{id}/comments")
    public List<IssueComment> findCommentsByIssueExternalId(@PathVariable String id) {
        Issue issue = getIssueByExternalId(id);

        return issueCommentService.findByIssueId(issue.getId());
    }

    @PostMapping(value = "/{id}/comments")
    public IssueComment addCommentByExternalId(@RequestBody IssueCommentDTO commentDto, @PathVariable String id, Principal principal) {
        Issue issue = getIssueByExternalId(id);

        ApplicationUser user = applicationUserService.findByUsername(principal.getName());

        IssueComment comment = new IssueComment();
        comment.setIssueId(issue.getId());
        comment.setText(commentDto.getText());
        comment.setCreatedDate(new Date());
        comment.setCreatedByUserId(user.getId());

        issueCommentService.save(comment);
        return comment;
    }

    @GetMapping("/{id}/transitions")
    public List<WorkflowTransition> getAvailableIssueTransitions(@PathVariable String id) {
        Issue issue = getIssueByExternalId(id);
        Optional<Workflow> workflow = workflowService.findById(issue.getWorkflowId());

        if (workflow.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Workflow not found."
            );
        }

        return workflowService.getAvailableTransitions(workflow.get(), issue.getWorkflowState());
    }

    @PostMapping("/{id}/transitions")
    public IssueDTO applyWorkflowTransition(
            @RequestBody WorkflowTransition transition,
            @PathVariable String id,
            Principal principal
    ) {
        Issue issue = getIssueByExternalId(id);

        try {
            ApplicationUser user = applicationUserService.findByUsername(principal.getName());
            workflowService.transitionIssue(issue, transition, user);

            return convertToDTO(issue);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    e.getMessage()
            );
        }
    }

    @GetMapping("/labels")
    public List<String> regexSearchLabels(){
        return labelService.findAll();
    }

    @GetMapping("/{id}/changes")
    public List<IssueChangeDTO> getAuditForIssue(@PathVariable String id) {
        Issue issue = getIssueByExternalId(id);
        return issueService.getAuditLogsForIssue(issue);
    }

    @GetMapping("/{id}/links")
    public List<IssueLinks> getLinksForIssue(@PathVariable String id) {
        Issue issue = getIssueByExternalId(id);
        return issueService.getLinksForIssueByExternalId(buildExternalKeyFromIssue(issue));
    }

    @PostMapping("/links")
    public IssueLinks createIssueLink(@RequestBody IssueLinks link) {
        getIssueByExternalId(link.getLinkPublicId());
        getIssueByExternalId(link.getNodePublicId());

        if (link.getLinkPublicId().equals(link.getNodePublicId())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "You cannot link an issue to itself.");
        }

        return issueService.saveIssueLinks(link);
    }

    @DeleteMapping("/links/{id}")
    public void deleteIssueLink(@PathVariable String id){
        IssueLinks link = issueService.findLinkById(id);

        if (link != null){
            issueService.deleteIssueLink(link);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue Link not found");
        }
    }

    @GetMapping("/types/{id}")
    public IssueType getIssueTypeById(@PathVariable String id) {
        return issueTypeSchemaService.findTypeById(id);
    }

    private Issue getIssueByExternalId(String id) {
        String projectKey = id.split("-")[0];
        long externalId = Long.parseLong(id.split("-")[1]);

        Project project = projectService.findByKey(projectKey);

        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PROJECT_NOT_FOUND_ERROR_MESSAGE);
        }

        Issue issue = issueService.findByProjectIdAndExternalId(project.getId(), externalId);

        if (issue == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }

        return issue;
    }

    private String buildExternalKeyFromIssue(Issue issue){
        Optional<Project> projectOptional = projectService.findById(issue.getProjectId());

        if (projectOptional.isEmpty()) {
            throw new RuntimeException(PROJECT_NOT_FOUND_ERROR_MESSAGE);
        }

        String projectKey = projectOptional.get().getProjectKey();
        long externalId = issue.getExternalId();
        return projectKey + "-" + externalId;
    }
    
    private IssueDTO convertToDTO(Issue issue) {
        IssueDTO dto = modelMapper.map(issue, IssueDTO.class);
        dto.setProjectKey(projectService.findById(issue.getProjectId()).get().getProjectKey());

        return dto;
    }
}
