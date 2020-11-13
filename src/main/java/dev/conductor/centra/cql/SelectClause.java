package dev.conductor.centra.cql;

import java.util.List;

public class SelectClause {
    private final List<String> fields;

    public SelectClause(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFields() {
        return fields;
    }
}
