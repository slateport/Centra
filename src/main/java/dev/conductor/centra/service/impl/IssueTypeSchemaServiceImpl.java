package dev.conductor.centra.service.impl;

import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.entities.IssueTypeSchema;
import dev.conductor.centra.repository.IssueTypeRepository;
import dev.conductor.centra.repository.IssueTypeSchemaRepository;
import dev.conductor.centra.service.IssueTypeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssueTypeSchemaServiceImpl implements IssueTypeSchemaService {

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
