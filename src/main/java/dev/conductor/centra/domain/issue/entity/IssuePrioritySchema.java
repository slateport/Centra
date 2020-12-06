package dev.conductor.centra.domain.issue.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IssuePrioritySchema {

    private String id;
    private String name;
    private List<String> priorityIds = new ArrayList<>();

    public void addPriority(IssuePriority priority) {
        this.priorityIds.add(priority.getId());
    }
}
