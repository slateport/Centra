package dev.conductor.centra.restcontrollers;

import dev.conductor.centra.entities.IssuePriority;
import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.entities.Project;
import dev.conductor.centra.service.IssuePrioritySchemaService;
import dev.conductor.centra.service.IssueService;
import dev.conductor.centra.service.IssueTypeSchemaService;
import dev.conductor.centra.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController extends BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    IssueTypeSchemaService issueTypeSchemaService;

    @Autowired
    IssuePrioritySchemaService prioritySchemaService;

    @GetMapping
    public List<Project> listAll() {
        return projectService.listAll();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable String id) {
        return projectService.findById(id).get();
    }

    @PostMapping
    public Project createProject (@RequestBody Project project, Principal principal) {
        if (!this.isAdmin(principal)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Not an administrator"
            );
        }


        if (projectService.findByKey(project.getProjectKey()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Project with key already exists"
            );
        }

        return projectService.create(project);
    }

    @GetMapping("/{id}/issueTypes")
    public List<IssueType> getIssueTypesForProject(@PathVariable String id) {
        Project project = null;
        Optional<Project> optionalProject = projectService.findById(id);

        if (optionalProject.isEmpty()){
            project = projectService.findByKey(id);
        }

        if (optionalProject.isEmpty() && project == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Project was not found"
            );
        }

        String issueTypeSchemaId = (project != null) ? project.getIssueTypeSchemaId()
                : optionalProject.get().getIssueTypeSchemaId();

        return issueTypeSchemaService.findTypeBySchema(
                issueTypeSchemaService.findSchemaById(issueTypeSchemaId)
        );
    }

    @GetMapping("/{id}/priorities")
    public List<IssuePriority> getPrioritiesForProject(@PathVariable String id) {
        Project project = null;
        Optional<Project> optionalProject = projectService.findById(id);

        if (optionalProject.isEmpty()){
            project = projectService.findByKey(id);
        }

        if (optionalProject.isEmpty() && project == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Project was not found"
            );
        }

        String prioritySchemaId = (project != null) ? project.getPrioritySchemaId()
                : optionalProject.get().getPrioritySchemaId();

        return prioritySchemaService.findPriorityBySchema(
                prioritySchemaService.findSchemaById(prioritySchemaId)
        );
    }

    @GetMapping("/priorities/{id}")
    public IssuePriority getPriorityById(@PathVariable String id) {
        return prioritySchemaService.findPriorityById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIssuesAndProject(@PathVariable String id, Principal principal) {
        if (!this.isAdmin(principal)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Not an administrator"
            );
        }
        Optional<Project> optionalProject = projectService.findById(id);

        if (optionalProject.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Project was not found"
            );
        }

        this.projectService.delete(optionalProject.get());
    }
}
