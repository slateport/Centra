package dev.conductor.centra.service;

import dev.conductor.centra.entities.IssueType;

public interface IssueTypeService {

    IssueType findById(String id);
    IssueType findByLabel(String label);
    IssueType save(IssueType issueType);
}
