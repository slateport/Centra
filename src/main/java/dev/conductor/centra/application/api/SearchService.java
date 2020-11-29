package dev.conductor.centra.application.api;

import dev.conductor.centra.domain.search.cql.CqlQuery;
import dev.conductor.centra.domain.issue.entity.Issue;

import java.util.List;

public interface SearchService {

    List<Issue> search(CqlQuery cqlQuery);
}
