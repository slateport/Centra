package dev.conductor.dataservices.cql;

import java.util.ArrayList;
import java.util.List;

public class CqlListenerImpl extends CqlBaseListener {

    private final List<Condition> conditions = new ArrayList<>();

    private Condition currentCondition;

    public List<Condition> getConditions() {
        return conditions;
    }

    @Override
    public void enterExpr(CqlParser.ExprContext ctx) {
        this.currentCondition = new AndCondition(
                stripQuotesFromString(ctx.getStart().getText()),
                null,
                stripQuotesFromString(ctx.getStop().getText())
        );
    }

    @Override
    public void exitExpr(CqlParser.ExprContext ctx) {
        this.conditions.add(this.currentCondition);
    }

    @Override
    public void enterOrdering_term(CqlParser.Ordering_termContext ctx) {
        int one = 1;
    }

    @Override
    public void enterOperator(CqlParser.OperatorContext ctx) {
        Operator operator = switch (ctx.getStop().getText()) {
            case "=" -> Operator.EQUALS;
            case "~" -> Operator.LIKE;
            case ">" -> Operator.GREATER_THAN;
            case "<" -> Operator.LESS_THAN;
            case "IN" -> Operator.IN;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.getStop().getText());
        };

        this.currentCondition.setOperator(operator);
    }

    protected String stripQuotesFromString(String input) {
        return input
                .replace("\"", "")
                .replace("'", "");
    }
}
