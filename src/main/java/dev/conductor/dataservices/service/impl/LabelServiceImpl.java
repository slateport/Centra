package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.entities.Label;
import dev.conductor.dataservices.repository.LabelRepository;
import dev.conductor.dataservices.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public List<Label> regexFindByValue(String search) {
        return labelRepository.regexFindByValue(search);
    }

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public Label findById(String id) {
        return labelRepository.findById(id).get();
    }

    @Override
    public Label findByValue(String value) {
        return labelRepository.findByValue(value);
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }
}
