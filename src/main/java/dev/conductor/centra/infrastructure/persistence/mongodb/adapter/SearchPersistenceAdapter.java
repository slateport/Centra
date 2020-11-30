package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.search.spi.SearchPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.IssueEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SearchPersistenceAdapter implements SearchPersistencePort {

    private final MongoOperations mongoOperations;
    private final ModelMapper modelMapper;

    @Autowired
    public SearchPersistenceAdapter(MongoOperations mongoOperations, ModelMapper modelMapper) {
        this.mongoOperations = mongoOperations;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Issue> find(Query query) {
        return mongoOperations.find(query, IssueEntity.class).stream().map(
                issueEntity -> modelMapper.map(issueEntity, Issue.class)
        ).collect(Collectors.toList());
    }
}
