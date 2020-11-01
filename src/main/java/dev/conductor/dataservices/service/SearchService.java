package dev.conductor.dataservices.service;

import dev.conductor.dataservices.cql.CqlQuery;
import dev.conductor.dataservices.entities.Issue;

import java.util.List;

public interface SearchService {

    List<Issue> search(CqlQuery cqlQuery);
}
