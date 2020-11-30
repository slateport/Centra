package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.issue.entity.IssueType;
import dev.conductor.centra.domain.issue.spi.IssueTypePersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.IssueTypeEntity;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.IssueTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueTypePersistenceAdapter implements IssueTypePersistencePort {

    private final IssueTypeRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public IssueTypePersistenceAdapter(IssueTypeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueType findByLabel(String label) {
        return modelMapper.map(repository.findByLabel(label), IssueType.class);
    }

    @Override
    public IssueType findById(String id) {
        return repository.findById(id).map(
                    issueTypeEntity -> modelMapper.map(issueTypeEntity, IssueType.class)
                ).orElse(null);
    }

    @Override
    public List<IssueType> findAll() {
        return repository.findAll().stream().map(
                    issueTypeEntity -> modelMapper.map(issueTypeEntity, IssueType.class)
                ).collect(Collectors.toList());
    }

    @Override
    public IssueType create(IssueType issueType) {
        return modelMapper.map(repository.save(modelMapper.map(issueType, IssueTypeEntity.class)), IssueType.class);
    }
}
