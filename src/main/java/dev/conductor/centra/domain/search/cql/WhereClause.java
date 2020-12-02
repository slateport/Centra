package dev.conductor.centra.domain.search.cql;

import dev.conductor.centra.domain.search.cql.conditions.Condition;

import java.util.List;

public class WhereClause {
    private List<Condition<String>> root;

    public WhereClause(List<Condition<String>> root) {
        this.root = root;
    }

    public List<Condition<String>> getRoot() {
        return root;
    }
}
