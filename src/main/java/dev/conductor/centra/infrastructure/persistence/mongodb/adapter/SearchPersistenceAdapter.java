package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.search.cql.Condition;
import dev.conductor.centra.domain.search.cql.CqlQuery;
import dev.conductor.centra.domain.search.spi.SearchPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.IssueEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
    public List<Issue> find(List<Condition> conditions) {

        Query query = new Query();

        for (Condition condition: conditions) {

            switch (condition.getOperator()) {
                case EQUALS :
                    query.addCriteria(Criteria.where(condition.getRhs()).is(condition.getLhs()));
                    break;

                case NOT_EQUALS:
                    query.addCriteria(Criteria.where(condition.getRhs()).ne(condition.getLhs()));
                    break;

                case GREATER_THAN:
                    query.addCriteria(Criteria.where(condition.getRhs()).gt(condition.getLhs()));
                    break;

                case LESS_THAN:
                    query.addCriteria(Criteria.where(condition.getRhs()).lt(condition.getLhs()));
                    break;

                case LIKE:
                    query.addCriteria(Criteria.where(condition.getRhs()).regex(condition.getLhs(), "i"));
                    break;

                case IN:
                    query.addCriteria(Criteria.where(condition.getRhs()).in(Collections.singletonList(condition.getLhs())));
                    break;

            }
        }




        return mongoOperations.find(query, IssueEntity.class).stream().map(
                issueEntity -> modelMapper.map(issueEntity, Issue.class)
        ).collect(Collectors.toList());
    }
}
