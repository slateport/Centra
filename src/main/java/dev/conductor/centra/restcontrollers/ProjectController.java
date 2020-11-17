package dev.conductor.centra.restcontrollers;

import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.entities.Project;
import dev.conductor.centra.service.IssueTypeSchemaService;
import dev.conductor.centra.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    IssueTypeSchemaService issueTypeSchemaService;

    @GetMapping
    public List<Project> listAll() {
        return projectService.listAll();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable String id) {
        return projectService.findById(id).get();
    }

    @PostMapping
    public Project createProject (@RequestBody Project project) {

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
}
