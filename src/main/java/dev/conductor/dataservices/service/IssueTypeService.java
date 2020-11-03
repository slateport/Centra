package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.IssueType;

import java.util.Optional;

public interface IssueTypeService {

    IssueType findById(String id);
    IssueType findByLabel(String label);
    IssueType save(IssueType issueType);
}
