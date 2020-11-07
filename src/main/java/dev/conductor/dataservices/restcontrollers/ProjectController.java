package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.entities.Project;
import dev.conductor.dataservices.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

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

        return projectService.save(project);
    }
}
