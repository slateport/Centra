package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.workflow.entities.Workflow;
import dev.conductor.centra.domain.workflow.spi.WorkflowPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.WorkflowEntity;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.WorkflowRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WorkflowPersistenceAdapter implements WorkflowPersistencePort {

    private final WorkflowRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public WorkflowPersistenceAdapter(WorkflowRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Workflow> findAll() {
        return repository.findAll()
                .stream()
                .map(workflowEntity -> modelMapper.map(workflowEntity, Workflow.class))
                .collect(Collectors.toList());
    }

    @Override
    public Workflow findByName(String name) {
        return modelMapper.map(repository.findByName(name), Workflow.class);
    }

    @Override
    public Workflow findById(String id) {
        return repository.findById(id)
                .map(workflowEntity -> modelMapper.map(workflowEntity, Workflow.class))
                .orElse(null);
    }

    @Override
    public Workflow save(Workflow workflow) {
        return modelMapper.map(repository.save(modelMapper.map(workflow, WorkflowEntity.class)), Workflow.class);
    }
}
