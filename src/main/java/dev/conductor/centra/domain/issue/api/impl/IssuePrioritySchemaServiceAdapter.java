package dev.conductor.centra.domain.issue.api.impl;

import dev.conductor.centra.domain.issue.api.IssuePrioritySchemaService;
import dev.conductor.centra.domain.issue.entity.IssuePriority;
import dev.conductor.centra.domain.issue.entity.IssuePrioritySchema;
import dev.conductor.centra.domain.issue.spi.IssuePrioritySchemaPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.IssuePriorityRepository;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.IssuePrioritySchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssuePrioritySchemaServiceAdapter implements IssuePrioritySchemaService {

    @Autowired
    private IssuePriorityRepository priorityRepository;

    @Autowired
    private IssuePrioritySchemaPersistencePort schemaPersistencePort;


    @Override
    public List<IssuePriority> findAllPriorities() {
        return priorityRepository.findAll();
    }

    @Override
    public IssuePriority findPriorityById(String id) {
        Optional<IssuePriority> priorityOptional = priorityRepository.findById(id);
        return (priorityOptional.isEmpty()) ? null : priorityOptional.get();
    }

    @Override
    public IssuePriority findPriorityByLabel(String label) {
        return priorityRepository.findByLabel(label);
    }

    @Override
    public IssuePriority createPriority(IssuePriority issuePriority) {
        return priorityRepository.save(issuePriority);
    }

    @Override
    public IssuePrioritySchema createSchema(IssuePrioritySchema schema) {
        return schemaPersistencePort.create(schema);
    }

    @Override
    public IssuePrioritySchema findSchemaByName(String name) {
        return schemaPersistencePort.findByName(name);
    }

    @Override
    public IssuePrioritySchema findSchemaById(String id) {
        return schemaPersistencePort.findById(id);
    }

    @Override
    public List<IssuePriority> findPriorityBySchema(IssuePrioritySchema schema) {
        List<IssuePriority> results = new ArrayList<>();

        for (String issueTypeId : schema.getPriorityIds()){
            results.add(findPriorityById(issueTypeId));
        }

        return results;
    }
}
