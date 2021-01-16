package dev.conductor.centra.application.controllers;

import dev.conductor.centra.domain.workflow.dto.WorkflowDTO;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.workflow.entities.StatePort;
import dev.conductor.centra.domain.workflow.entities.Workflow;
import dev.conductor.centra.domain.project.api.ProjectService;
import dev.conductor.centra.domain.workflow.api.WorkflowService;
import dev.conductor.centra.domain.workflow.entities.WorkflowState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
                dto.getTransitions(),
                dto.getStates().size() > 3 ? 1 : 0
        );

        return workflowService.create(workflow);
    }

    @GetMapping("/{id}")
    Workflow getById(@PathVariable String id){
        Workflow workflow = workflowService.findById(id);
        if(workflow.getLevel() == null) {
            workflow.setLevel(workflow.getStates().size() > 3 ? 1 : 0);
        }
        List<WorkflowState> orgStates = workflow.getStates();
        orgStates.forEach(workflowState -> {
            if(orgStates.size() > 3) {
                switch (workflowState.getLabel()) {
                    case "PROPOSE":
                        List<StatePort> propPorts = new ArrayList<StatePort>();
                        propPorts.add(new StatePort("input", "RM", 1, null));
                        propPorts.add(new StatePort("input", "B3", 6, null));
                        propPorts.add(new StatePort("output", "B1", null, 6));
                        propPorts.add(new StatePort("input", "LM", null, null));
                        workflowState.setPorts(propPorts);
                        break;
                    case "IN PROGRESS":
                        List<StatePort> doingPorts = new ArrayList<StatePort>();
                        doingPorts.add(new StatePort("input", "T1", 5, null));
                        doingPorts.add(new StatePort("input", "T3", 3, null));
                        doingPorts.add(new StatePort("output", "RM", null, 3));
                        doingPorts.add(new StatePort("output", "B3", null, 2));
                        doingPorts.add(new StatePort("input", "BM", 2, null));
                        doingPorts.add(new StatePort("output", "B1", null, 5));
                        doingPorts.add(new StatePort("output", "B1", null, 6));
                        doingPorts.add(new StatePort("output", "LM", null, 0));
                        workflowState.setPorts(doingPorts);
                        break;
                    case "ON HOLD":
                        List<StatePort> holdPorts = new ArrayList<StatePort>();
                        holdPorts.add(new StatePort("output", "BM", null, 1));
                        holdPorts.add(new StatePort("output", "RM", null, 3));
                        holdPorts.add(new StatePort("output", "B1", null, 6));
                        holdPorts.add(new StatePort("input", "LM", 1, null));
                        workflowState.setPorts(holdPorts);
                        break;
                    case "PENDING RELEASE":
                        List<StatePort> releasePorts = new ArrayList<StatePort>();
                        releasePorts.add(new StatePort("output", "T1", null, 1));
                        releasePorts.add(new StatePort("output", "B3", null, 4));
                        releasePorts.add(new StatePort("input", "BM", 2, null));
                        releasePorts.add(new StatePort("output", "B1", null, 6));
                        releasePorts.add(new StatePort("input", "LM", 1, null));
                        workflowState.setPorts(releasePorts);
                        break;
                    case "CLOSED":
                        List<StatePort> closePorts = new ArrayList<StatePort>();
                        closePorts.add(new StatePort("input", "TM", 3, null));
                        workflowState.setPorts(closePorts);
                        break;
                    case "CANCELLED":
                        List<StatePort> cancelPorts = new ArrayList<StatePort>();
                        cancelPorts.add(new StatePort("output", "T1", null, 1));
                        cancelPorts.add(new StatePort("input", "RM", 1, null));
                        workflowState.setPorts(cancelPorts);
                        break;
                    case "REJECTED":
                        List<StatePort> rejectPorts = new ArrayList<StatePort>();
                        rejectPorts.add(new StatePort("input", "T1", 0, null));
                        rejectPorts.add(new StatePort("output", "TM", null, 0));
                        rejectPorts.add(new StatePort("input", "T3", 1, null));
                        rejectPorts.add(new StatePort("input", "T3", 2, null));
                        rejectPorts.add(new StatePort("input", "T3", 3, null));
                        workflowState.setPorts(rejectPorts);
                        break;
                }
            } else {
                switch (workflowState.getLabel()) {
                    case "TO DO":
                        List<StatePort> todoPorts = new ArrayList<StatePort>();
                        todoPorts.add(new StatePort("output", "BM", null, 1));
                        todoPorts.add(new StatePort("input", "LM", null, null));
                        workflowState.setPorts(todoPorts);
                        break;
                    case "IN PROGRESS":
                        List<StatePort> doingPorts = new ArrayList<StatePort>();
                        doingPorts.add(new StatePort("input", "TM", 0, null));
                        doingPorts.add(new StatePort("output", "BM", null, 2));
                        workflowState.setPorts(doingPorts);
                        break;
                    case "DONE":
                        List<StatePort> donePorts = new ArrayList<StatePort>();
                        donePorts.add(new StatePort("input", "TM", 1, null ));
                        workflowState.setPorts(donePorts);
                        break;
                }
            }
        });
        return workflow;
    }

    @PutMapping("/{id}")
    Workflow save(@PathVariable String id, @RequestBody EntityModel<WorkflowDTO> workflowDto) {
        WorkflowDTO dto = workflowDto.getContent();
        assert dto != null;

        Workflow originalWorkflow = workflowService.findById(id);

        Workflow workflow = new Workflow();
        workflow.setId(id);
        workflow.setName(dto.getName() == null ? originalWorkflow.getName() : dto.getName());
        workflow.setStates(dto.getStates() == null ? originalWorkflow.getStates() : dto.getStates());
        workflow.setTransitions(dto.getTransitions() == null ? originalWorkflow.getTransitions() : dto.getTransitions());

        return workflowService.save(workflow);
    }
}
