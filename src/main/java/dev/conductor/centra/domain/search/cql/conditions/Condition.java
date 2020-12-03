package dev.conductor.centra.domain.search.cql.conditions;

import java.util.List;

public interface Condition<T> {
    void setOperator(Operator operator);
    Operator getOperator();
    String entityProperty();
    List<T> getValue();
    void setValue(List<T> value);
    void addValue(T value);
    SearchType searchType();

}
