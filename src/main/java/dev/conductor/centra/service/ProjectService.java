package dev.conductor.centra.service;


import dev.conductor.centra.entities.Project;

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
