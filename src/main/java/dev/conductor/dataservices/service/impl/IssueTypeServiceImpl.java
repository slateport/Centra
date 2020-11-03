package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.data.defualt.DefaultInstall;
import dev.conductor.dataservices.entities.IssueType;
import dev.conductor.dataservices.repository.IssueTypeRepository;
import dev.conductor.dataservices.service.IssueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueTypeServiceImpl implements IssueTypeService {

    @Autowired
    private IssueTypeRepository repository;

    @Override
    public IssueType findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public IssueType findByLabel(String label) {
        return repository.findByLabel(label);
    }

    @Override
    public IssueType save(IssueType issueType) {
        return repository.save(issueType);
    }
}
