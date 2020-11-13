package dev.conductor.centra.cql;

public interface Condition {
    void setOperator(Operator operator);
    Operator getOperator();
    String getRhs();
    String getLhs();
}
