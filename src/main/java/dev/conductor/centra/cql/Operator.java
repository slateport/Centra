package dev.conductor.centra.cql;

public enum Operator {
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER_THAN("="),
    LESS_THAN("="),
    LIKE("~"),
    IN("IN");

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
