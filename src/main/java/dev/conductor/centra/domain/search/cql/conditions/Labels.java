package dev.conductor.centra.domain.search.cql.conditions;

public class Labels extends AbstractStringListValueCondition {
    @Override
    public String entityProperty() {
        return "labels";
    }

    @Override
    public SearchType searchType() {
        return SearchType.CRITERIA;
    }
}
