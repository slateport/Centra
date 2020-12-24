package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.OperatorTypeEnum;

public class Expression {

    private String leftValue;
    private OperatorTypeEnum operatorType;
    private AbstractRightValue rightValue;

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public OperatorTypeEnum getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorTypeEnum operatorType) {
        this.operatorType = operatorType;
    }

    public AbstractRightValue getRightValue() {
        return rightValue;
    }

    public void setRightValue(AbstractRightValue rightValue) {
        this.rightValue = rightValue;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "leftValue='" + leftValue + '\'' +
                ", operatorType=" + operatorType +
                ", rightValue=" + rightValue +
                '}';
    }
}
