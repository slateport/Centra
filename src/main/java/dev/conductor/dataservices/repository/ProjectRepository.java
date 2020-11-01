package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {

    Project findByProjectKey(String key);
    Project findByProjectName(String name);
    Optional<Project> findById(String id);
}
