package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.entities.Project;
import dev.conductor.dataservices.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public List<Project> listAll() {
        return projectService.listAll();
    }

    @PostMapping
    public Project createProject (@RequestBody Project project) {

        if (projectService.findByKey(project.getProjectKey()) != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Project with key already exists");
        }

        return projectService.save(project);
    }
}
