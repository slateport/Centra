package dev.conductor.centra.infrastructure.persistence.mongodb;

import dev.conductor.centra.entities.IssueComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueCommentRepository extends MongoRepository<IssueComment, String> {

    List<IssueComment> findByIssueIdOrderByCreatedDateAsc(String id);
}