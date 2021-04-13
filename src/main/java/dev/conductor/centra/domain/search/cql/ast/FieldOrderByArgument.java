package dev.conductor.centra.domain.search.cql.ast;

public class FieldOrderByArgument implements AbstractOrderByArgument {

    String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "FieldOrderByArgument{" +
                "field='" + field + '\'' +
                '}';
    }
}
