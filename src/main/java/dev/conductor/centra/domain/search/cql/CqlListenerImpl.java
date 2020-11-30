package dev.conductor.centra.domain.search.cql;

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
    public void enterOperator(CqlParser.OperatorContext ctx) {
        Operator operator;

        switch(ctx.getStop().getText()){
            case "=":
                operator = Operator.EQUALS;
                break;

            case "~":
                operator = Operator.LIKE;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + ctx.getStop().getText());

        }

        this.currentCondition.setOperator(operator);
    }

    protected String stripQuotesFromString(String input) {
        return input
                .replace("\"", "")
                .replace("'", "");
    }
}
