package dev.conductor.centra.service.impl;

import dev.conductor.centra.entities.Project;
import dev.conductor.centra.repository.ProjectRepository;
import dev.conductor.centra.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project findByKey(String key) {
        return projectRepository.findByProjectKey(key);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findByProjectName(name);
    }
}
