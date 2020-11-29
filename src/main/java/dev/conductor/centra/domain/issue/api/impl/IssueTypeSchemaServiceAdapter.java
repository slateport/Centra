package dev.conductor.centra.domain.issue.api.impl;

import dev.conductor.centra.domain.issue.api.IssueTypeSchemaService;
import dev.conductor.centra.domain.issue.entity.IssueType;
import dev.conductor.centra.domain.issue.entity.IssueTypeSchema;
import dev.conductor.centra.infrastructure.persistence.mongodb.IssueTypeRepository;
import dev.conductor.centra.infrastructure.persistence.mongodb.IssueTypeSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssueTypeSchemaServiceAdapter implements IssueTypeSchemaService {

    @Autowired
    private IssueTypeRepository typeRepository;

    @Autowired
    private IssueTypeSchemaRepository schemaRepository;

    @Override
    public List<IssueType> findAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public IssueType findTypeById(String id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public IssueType findTypeByLabel(String label) {
        return typeRepository.findByLabel(label);
    }

    @Override
    public IssueType createType(IssueType issueType) {
        return typeRepository.save(issueType);
    }

    @Override
    public IssueTypeSchema createSchema(IssueTypeSchema schema) {
        return schemaRepository.save(schema);
    }

    @Override
    public IssueTypeSchema findSchemaByName(String name) {
        return schemaRepository.findByName(name);
    }

    @Override
    public IssueTypeSchema findSchemaById(String id) {
        Optional<IssueTypeSchema> optional = schemaRepository.findById(id);

        return (optional.isEmpty()) ? null : optional.get();
    }

    @Override
    public List<IssueType> findTypeBySchema(IssueTypeSchema schema) {
        List<IssueType> results = new ArrayList<>();

        for (String issueTypeId : schema.getIssueTypeIds()){
            results.add(findTypeById(issueTypeId));
        }

        return results;
    }
}
