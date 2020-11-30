package dev.conductor.centra.infrastructure.persistence.mongodb.repository;

import dev.conductor.centra.domain.workflow.entities.Workflow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkflowRepository extends MongoRepository<Workflow, String> {

    List<Workflow> findAll();
    Workflow findByName(String name);
}
