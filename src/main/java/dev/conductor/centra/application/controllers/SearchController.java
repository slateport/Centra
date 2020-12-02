package dev.conductor.centra.application.controllers;

import dev.conductor.centra.domain.search.cql.CqlQuery;
import dev.conductor.centra.domain.search.cql.Parser;
import dev.conductor.centra.domain.issue.dto.IssueDTO;
import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.project.entity.Project;
import dev.conductor.centra.domain.project.api.ProjectService;
import dev.conductor.centra.domain.search.api.SearchService;
import dev.conductor.centra.domain.search.cql.conditions.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private Parser parser;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<IssueDTO> searchByCql(@RequestParam String cql){
        List<Condition<String>> conditions = parser.parse(cql);

        if (conditions.size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Search has no parameters"
            );
        }

        final List<IssueDTO> dtoList = new ArrayList<>();

        for (Issue issue: searchService.search(conditions)) {
            Project project = projectService.findById(issue.getProjectId());

            try {
                IssueDTO issueDTO = modelMapper.map(issue, IssueDTO.class);
                issueDTO.setProjectKey(project.getProjectKey());

                dtoList.add(issueDTO);
            } catch (RuntimeException e) {
                System.out.println(issue.getId() + " encountered an issue");
            }

        }

        return dtoList;
    }
}
