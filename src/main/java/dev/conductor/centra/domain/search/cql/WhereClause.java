package dev.conductor.centra.domain.search.cql;

import java.util.List;

public class WhereClause {
    private List<Condition> root;

    public WhereClause(List<Condition> root) {
        this.root = root;
    }

    public List<Condition> getRoot() {
        return root;
    }
}
