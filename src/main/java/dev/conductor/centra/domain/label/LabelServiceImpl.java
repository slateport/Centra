package dev.conductor.centra.domain.label;

import dev.conductor.centra.domain.issue.entity.Issue;
import org.springframework.beans.factory.annotation.Autowired;
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
