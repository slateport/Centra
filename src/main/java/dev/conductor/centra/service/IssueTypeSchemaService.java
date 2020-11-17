package dev.conductor.centra.service;

import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.entities.IssueTypeSchema;

import java.util.List;

public interface IssueTypeSchemaService {

    List<IssueType> findAllTypes();
    IssueType findTypeById(String id);
    IssueType findTypeByLabel(String label);
    IssueType createType(IssueType issueType);
    IssueTypeSchema createSchema(IssueTypeSchema schema);
    IssueTypeSchema findSchemaByName(String name);
    IssueTypeSchema findSchemaById(String id);
    List<IssueType> findTypeBySchema(IssueTypeSchema schema);
}
