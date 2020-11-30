package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.project.spi.ProjectPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.ProjectEntity;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProjectPersistenceAdapter implements ProjectPersistencePort {

    private final ProjectRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectPersistenceAdapter(ProjectRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Project findByProjectKey(String key) {
        return modelMapper.map(repository.findByProjectKey(key), Project.class);
    }

    @Override
    public Project findByProjectName(String name) {
        return modelMapper.map(repository.findByProjectName(name), Project.class);
    }

    @Override
    public Project findById(String id) {
        return repository.findById(id).map(
            projectEntity -> modelMapper.map(projectEntity, Project.class)
        ).orElse(null);
    }

    @Override
    public Project create(Project project) {
        return modelMapper.map(repository.save(modelMapper.map(project, ProjectEntity.class)), Project.class);
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll()
                .stream()
                .map(projectEntity -> modelMapper.map(projectEntity, Project.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Project project) {
        repository.delete(modelMapper.map(project, ProjectEntity.class));
    }
}
