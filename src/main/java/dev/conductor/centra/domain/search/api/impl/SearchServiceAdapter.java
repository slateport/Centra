package dev.conductor.centra.domain.search.api.impl;

import dev.conductor.centra.domain.search.api.SearchService;
import dev.conductor.centra.domain.search.cql.AndCondition;
import dev.conductor.centra.domain.search.cql.Condition;
import dev.conductor.centra.domain.search.cql.CqlQuery;
import dev.conductor.centra.domain.search.cql.Operator;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.applicationUser.api.ApplicationUserService;
import dev.conductor.centra.domain.project.api.ProjectService;
import dev.conductor.centra.domain.search.spi.SearchPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.IssueEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceAdapter implements SearchService {

    @Autowired
    private MongoOperations mongoOps;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private SearchPersistencePort persistencePort;

    @Override
    public List<Issue>  search(CqlQuery cqlQuery) {
        Query query = new Query();

        for (Condition condition: cqlQuery.getWhere().getRoot()) {
            condition = normalizeCondition(condition);

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

        return persistencePort.find(query);
    }

    private Condition normalizeCondition(Condition condition) {

        switch (condition.getRhs().toLowerCase()){
            case "project":
                Project project = projectService.findByName(condition.getLhs());
                return new AndCondition("projectId", Operator.EQUALS, (project != null) ? project.getId() : null);

            case "projectkey":
                Project projectByKey = projectService.findByKey(condition.getLhs().toUpperCase());
                return new AndCondition("projectId", Operator.EQUALS, (projectByKey != null) ? projectByKey.getId() : null);

            case "status":
                return new AndCondition("workflowState.label", Operator.EQUALS, condition.getLhs());

            case "assignee":
                ApplicationUser user = applicationUserService.findByUsername(condition.getLhs());
                if (condition.getLhs().equals("Unassigned") || user == null){
                    return new AndCondition("assigneeId", Operator.EQUALS, null);
                } else {
                    return new AndCondition("assigneeId", Operator.EQUALS, user.getId());
                }

            case "label":
                return new AndCondition("labels", Operator.IN, condition.getLhs());

            default:
                return condition;

        }
    }

}
