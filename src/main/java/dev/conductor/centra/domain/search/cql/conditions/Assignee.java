package dev.conductor.centra.domain.search.cql.conditions;

public class Assignee extends AbstractStringListValueCondition {

    @Override
    public String entityProperty() {
        return "assigneeId";
    }
}
