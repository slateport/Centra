package dev.conductor.dataservices.service;


import dev.conductor.dataservices.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project findByKey(String key);
    Project findByName(String name);
    Optional<Project> findById(String id);
    Project save(Project project);
    List<Project> listAll();
}
