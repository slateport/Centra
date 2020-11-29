package dev.conductor.centra.domain.search.cql;

public interface Condition {
    void setOperator(Operator operator);
    Operator getOperator();
    String getRhs();
    String getLhs();
}
