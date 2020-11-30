package dev.conductor.centra.infrastructure.persistence.mongodb.repository;

import dev.conductor.centra.domain.issue.entity.IssueLinks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueLinksRepository extends MongoRepository<IssueLinks, String> {
    List<IssueLinks> findByNodePublicId(String id);
    List<IssueLinks> findByLinkPublicId(String id);
}
