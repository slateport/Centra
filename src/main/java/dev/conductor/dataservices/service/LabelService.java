package dev.conductor.dataservices.service;

import dev.conductor.dataservices.entities.Label;

import java.util.List;

public interface LabelService {

    List<Label> findAll();
    List<Label> regexFindByValue(String search);
    Label findById(String id);
    Label findByValue(String value);
    Label save(Label label);
}
