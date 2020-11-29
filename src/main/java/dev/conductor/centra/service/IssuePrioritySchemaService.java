package dev.conductor.centra.service;

import dev.conductor.centra.entities.IssuePriority;
import dev.conductor.centra.entities.IssuePrioritySchema;

import java.util.List;

public interface IssuePrioritySchemaService {

    List<IssuePriority> findAllPriorities();
    IssuePriority findPriorityById(String id);
    IssuePriority findPriorityByLabel(String label);
    IssuePriority createPriority(IssuePriority issuePriority);
    IssuePrioritySchema createSchema(IssuePrioritySchema schema);
    IssuePrioritySchema findSchemaByName(String name);
    IssuePrioritySchema findSchemaById(String Id);
    List<IssuePriority> findPriorityBySchema(IssuePrioritySchema schema);
}
