package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.Label;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LabelRepository extends MongoRepository<Label, String> {
    Label findByValue(String value);

    @Query("{ 'value' :{ $regex: ?0, $options: 'i' }}")
    List<Label> regexFindByValue(String search);
}
