package dev.conductor.centra.domain.search.api.impl;

import dev.conductor.centra.domain.search.api.SearchService;
import dev.conductor.centra.domain.search.cql.conditions.Condition;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.applicationUser.api.ApplicationUserService;
import dev.conductor.centra.domain.project.api.ProjectService;
import dev.conductor.centra.domain.search.cql.conditions.ProjectKeys;
import dev.conductor.centra.domain.search.spi.SearchPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchServiceAdapter implements SearchService {

    private final ProjectService projectService;
    private final ApplicationUserService applicationUserService;
    private final SearchPersistencePort persistencePort;

    @Autowired
    public SearchServiceAdapter(
            ProjectService projectService,
            ApplicationUserService applicationUserService,
            SearchPersistencePort persistencePort
    ) {
        this.projectService = projectService;
        this.applicationUserService = applicationUserService;
        this.persistencePort = persistencePort;
    }

    @Override
    public List<Issue> search(List<Condition<String>> conditions) {

        List<Condition> conditionsEnriched = conditions
                .stream()
                .map(this::enrichCondition)
                .collect(Collectors.toList());

        return persistencePort.find(conditionsEnriched);
    }

    private Condition enrichCondition(Condition condition) {

//        switch (condition.getRhs().toLowerCase()){
//            case "assignee":
//                ApplicationUser user = applicationUserService.findByUsername(condition.getLhs());
//                if (condition.getLhs().equals("Unassigned") || user == null){
//                    return new AndCondition("assigneeId", Operator.EQUALS, null);
//                } else {
//                    return new AndCondition("assigneeId", Operator.EQUALS, user.getId());
//                }
//
//            case "label":
//                return new AndCondition("labels", Operator.IN, condition.getLhs());
//
//            default:
//                return condition;
//
//        }

        if (condition instanceof ProjectKeys) {
            List<Project> projects = (List<Project>) condition.getValue()
                    .stream()
                    .map(key -> projectService.findByKey(((String) key).toUpperCase()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            List<String> projectIds = projects.stream().map(Project::getId).collect(Collectors.toList());

            ProjectKeys criteria = new ProjectKeys();
            criteria.setValue(projectIds);
            criteria.setOperator(condition.getOperator());

            return criteria;
        }



        return condition;
    }

}
