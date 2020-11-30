package dev.conductor.centra.application.controllers;

import dev.conductor.centra.domain.workflow.dto.WorkflowDTO;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.workflow.entities.Workflow;
import dev.conductor.centra.domain.project.api.ProjectService;
import dev.conductor.centra.domain.workflow.api.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    List<Workflow> findAll() {
        return workflowService.findAll();
    }

    @PostMapping
    Workflow save(@RequestBody EntityModel<WorkflowDTO> workflowDto) {
        WorkflowDTO dto = workflowDto.getContent();
        assert dto != null;
        Project project = projectService.findById(dto.getProjectId());

        if (project == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "No such project exists"
            );
        }

        List<Workflow> existingWorkflows = workflowService.findAll();

        Workflow wfl = existingWorkflows.stream()
                .filter(e -> e.getName().equals(dto.getName()))
                .findFirst()
                .orElse(null);

        if (wfl != null) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Duplicate workflow with same name exists"
            );
        }

        Workflow workflow = new Workflow(
                dto.getName(),
                dto.getStates(),
                dto.getTransitions()
        );

        workflowService.create(workflow);

        return workflow;
    }

    @GetMapping("/{id}")
    Workflow getById(@PathVariable String id){
        return workflowService.findById(id);
    }
}
