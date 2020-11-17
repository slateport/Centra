package dev.conductor.centra.service.impl;

import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.entities.IssueTypeSchema;
import dev.conductor.centra.repository.IssueTypeRepository;
import dev.conductor.centra.repository.IssueTypeSchemaRepository;
import dev.conductor.centra.service.IssueTypeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
