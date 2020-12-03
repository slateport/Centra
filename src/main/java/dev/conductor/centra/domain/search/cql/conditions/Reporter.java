package dev.conductor.centra.domain.search.cql.conditions;

public class Reporter extends AbstractStringListValueCondition {

    @Override
    public String entityProperty() {
        return "createdByUserId";
    }

    @Override
    public SearchType searchType() {
        return SearchType.CRITERIA;
    }
}
