package dev.conductor.centra.domain.search.spi;

import dev.conductor.centra.domain.issue.entity.Issue;
import dev.conductor.centra.domain.search.cql.Condition;

import java.util.List;

public interface SearchPersistencePort {

    List<Issue> find(List<Condition> conditions);
}
