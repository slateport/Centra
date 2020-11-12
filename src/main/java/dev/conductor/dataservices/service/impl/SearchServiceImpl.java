package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.cql.AndCondition;
import dev.conductor.dataservices.cql.Condition;
import dev.conductor.dataservices.cql.CqlQuery;
import dev.conductor.dataservices.cql.Operator;
import dev.conductor.dataservices.entities.ApplicationUser;
import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Project;
import dev.conductor.dataservices.repository.IssueRepository;
import dev.conductor.dataservices.service.ApplicationUserService;
import dev.conductor.dataservices.service.ProjectService;
import dev.conductor.dataservices.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MongoOperations mongoOps;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Override
    public List<Issue>  search(CqlQuery cqlQuery) {
        Query query = new Query();

        for (Condition condition: cqlQuery.getWhere().getRoot()) {
            condition = normalizeCondition(condition);

            switch (condition.getOperator()) {
                case EQUALS -> query.addCriteria(
                        Criteria.where(condition.getRhs()).is(condition.getLhs())
                );
                case NOT_EQUALS -> query.addCriteria(
                        Criteria.where(condition.getRhs()).ne(condition.getLhs())
                );
                case GREATER_THAN -> query.addCriteria(
                        Criteria.where(condition.getRhs()).gt(condition.getLhs())
                );
                case LESS_THAN -> query.addCriteria(
                        Criteria.where(condition.getRhs()).lt(condition.getLhs())
                );
                case LIKE -> query.addCriteria(
                        Criteria.where(condition.getRhs()).
                                regex(".*m" + condition.getLhs() + ".*", "i")
                );
                case IN ->  query.addCriteria(
                        Criteria.where(condition.getRhs()).in(Collections.singletonList(condition.getLhs()))
                );
            }
        }

        return mongoOps.find(query, Issue.class);
    }

    private Condition normalizeCondition(Condition condition) {

        switch (condition.getRhs().toLowerCase()){
            case "project":
                Project project = projectService.findByName(condition.getLhs());
                return new AndCondition("projectId", Operator.EQUALS, (project != null) ? project.getId() : null);

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
