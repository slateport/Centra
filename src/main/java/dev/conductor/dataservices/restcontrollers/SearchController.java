package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.cql.CqlQuery;
import dev.conductor.dataservices.cql.Parser;
import dev.conductor.dataservices.dto.IssueDTO;
import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Project;
import dev.conductor.dataservices.service.ProjectService;
import dev.conductor.dataservices.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private Parser parser;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<IssueDTO> searchByCql(@RequestParam String cql){
        CqlQuery query = parser.parse(cql);

        if (query.getWhere().getRoot().size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Search has no parameters"
            );
        }

        final List<IssueDTO> dtoList = new ArrayList<>();

        for (Issue issue: searchService.search(query)) {
            Optional<Project> project = projectService.findById(issue.getProjectId());

            dtoList.add(new IssueDTO(
                    issue.getId(),
                    issue.getExternalId(),
                    project.get().getProjectKey(),
                    issue.getTitle(),
                    issue.getDescription(),
                    issue.getCreatedDate(),
                    issue.getLastModifiedDate(),
                    issue.getProjectId(),
                    issue.getWorkflowState(),
                    issue.getWorkflowId(),
                    issue.getCreatedByUserId(),
                    issue.getAssigneeId(),
                    issue.getLastModifiedByUserId(),
                    issue.getIssuePriority(),
                    issue.getLabels()
            ));
        }

        return dtoList;
    }
}
