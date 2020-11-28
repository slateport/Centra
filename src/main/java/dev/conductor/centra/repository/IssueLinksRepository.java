package dev.conductor.centra.repository;

import dev.conductor.centra.entities.IssueLinks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueLinksRepository extends MongoRepository<IssueLinks, String> {
    List<IssueLinks> findByNodePublicId(String id);
    List<IssueLinks> findByLinkPublicId(String id);
}
