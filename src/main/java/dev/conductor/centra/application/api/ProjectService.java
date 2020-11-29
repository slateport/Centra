package dev.conductor.centra.application.api;


import dev.conductor.centra.domain.project.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project findByKey(String key);
    Project findByName(String name);
    Optional<Project> findById(String id);
    Project create(Project project);
    List<Project> listAll();
    void delete(Project project);
}
