package dev.conductor.centra.domain.search.spi;

import dev.conductor.centra.domain.issue.entity.Issue;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface SearchPersistencePort {

    List<Issue> find(Query query);
}
