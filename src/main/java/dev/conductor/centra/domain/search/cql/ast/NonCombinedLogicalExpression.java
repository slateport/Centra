package dev.conductor.centra.domain.search.cql.ast;

public class NonCombinedLogicalExpression extends AbstractLogicalExpression {
    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "NonCombinedLogicalExpression{" +
                "expression=" + expression +
                ", negated=" + isNegated() +
                ", braced=" + isBraced() +
                '}';
    }

    @Override
    public String prettyPrint(int level) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n");

        for (int i=0; i < level; i++) {
            stringBuilder.append(INDENT);
        }
        stringBuilder.append(toString());

        return stringBuilder.toString();
    }
}
