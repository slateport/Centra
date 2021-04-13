package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.LogicalOperatorEnum;

public class CombinedLogicalExpression extends AbstractLogicalExpression {
    private LogicalOperatorEnum logicalOperator;
    private AbstractLogicalExpression left, right;

    public LogicalOperatorEnum getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(LogicalOperatorEnum logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public AbstractLogicalExpression getLeft() {
        return left;
    }

    public void setLeft(AbstractLogicalExpression left) {
        this.left = left;
    }

    public AbstractLogicalExpression getRight() {
        return right;
    }

    public void setRight(AbstractLogicalExpression right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "CombinedLogicalExpression{" +
                "logicalOperator=" + logicalOperator +
                ", left=" + left +

                ", negated=" + isNegated() +
                ", braced=" + isBraced() +

                ", right=" + right +
                '}';
    }

    @Override
    public String prettyPrint(int level) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n");

        for (int i = 0; i < level; i++) {
            stringBuilder.append(INDENT);
        }
        stringBuilder.append("CombinedLogicalExpression{" +
                "logicalOperator=" + logicalOperator +
                ", left=" + left.prettyPrint(level + 1) +

                ", negated=" + isNegated() +
                ", braced=" + isBraced() +

                ", right=" + right.prettyPrint(level + 1) +
                '}');

        return stringBuilder.toString();
    }

}
