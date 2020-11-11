package dev.conductor.dataservices.service.impl;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import dev.conductor.dataservices.entities.Issue;
import dev.conductor.dataservices.entities.Label;
import dev.conductor.dataservices.repository.LabelRepository;
import dev.conductor.dataservices.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> findAll() {
        List<String> arrayList = new ArrayList<>();
        List<Object> object = mongoTemplate.query(Issue.class).distinct("labels").all();
        for (Object object2 : object) {
            String label = (String) object2;
            arrayList.add(label);
        }

        return arrayList;
    }
}
