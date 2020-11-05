package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.cql.CqlQuery;
import dev.conductor.dataservices.cql.Parser;
import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private Parser parser;

    @GetMapping
    public List<Issue> searchByCql(@RequestParam String cql){
        CqlQuery query = parser.parse(cql);

        if (query.getWhere().getRoot().size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Search has no parameters"
            );
        }

        return searchService.search(query);
    }
}
