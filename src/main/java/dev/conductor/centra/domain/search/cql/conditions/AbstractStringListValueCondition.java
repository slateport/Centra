package dev.conductor.centra.domain.search.cql.conditions;

import dev.conductor.centra.domain.search.cql.Operator;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractStringListValueCondition implements Condition<String> {
    private Operator operator;
    private List<String> value = new ArrayList<>();

    @Override
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public Operator getOperator() {
        return this.operator;
    }

    @Override
    public List<String> getValue() {
        return this.value;
    }

    @Override
    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public void addValue(String value) {
        this.value.add(value);
    }
}
