package dev.conductor.centra.infrastructure.persistence.mongodb;

import dev.conductor.centra.domain.project.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {

    Project findByProjectKey(String key);
    Project findByProjectName(String name);
    Optional<Project> findById(String id);
}
