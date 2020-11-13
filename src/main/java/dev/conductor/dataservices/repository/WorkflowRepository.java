package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.Workflow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkflowRepository extends MongoRepository<Workflow, String> {

    List<Workflow> findAll();
}
