package dev.conductor.centra.service;

import dev.conductor.centra.cql.CqlQuery;
import dev.conductor.centra.entities.Issue;

import java.util.List;

public interface SearchService {

    List<Issue> search(CqlQuery cqlQuery);
}
