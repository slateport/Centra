package dev.conductor.centra.repository;

import dev.conductor.centra.entities.Workflow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkflowRepository extends MongoRepository<Workflow, String> {

    List<Workflow> findAll();
    Workflow findByName(String name);
}
