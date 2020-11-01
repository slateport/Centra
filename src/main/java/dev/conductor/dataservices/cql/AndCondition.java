package dev.conductor.dataservices.cql;

public class AndCondition implements Condition {

    private String rhs;
    private Operator operator;
    private String lhs;

    public AndCondition(String rhs, Operator operator, String lhs) {
        this.rhs = rhs;
        this.operator = operator;
        this.lhs = lhs;
    }

    public String getRhs() {
        return rhs;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getLhs() {
        return lhs;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
