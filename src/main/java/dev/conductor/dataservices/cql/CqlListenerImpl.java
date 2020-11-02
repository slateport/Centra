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

    @Override
    public void enterLiteral_value(CqlParser.Literal_valueContext ctx) {
        int one = 1;
    }

    @Override
    public void exitLiteral_value(CqlParser.Literal_valueContext ctx) {
        int one = 1;
    }

    @Override
    public void enterLiteral_list(CqlParser.Literal_listContext ctx) {
        int one = 1;
    }

    @Override
    public void exitLiteral_list(CqlParser.Literal_listContext ctx) {
        int one = 1;
    }

    @Override
    public void enterKeyword(CqlParser.KeywordContext ctx) {
        int one = 1;
    }

    @Override
    public void exitKeyword(CqlParser.KeywordContext ctx) {
        int one = 1;
    }

    @Override
    public void enterState_name(CqlParser.State_nameContext ctx) {
        int one = 1;
    }

    @Override
    public void exitState_name(CqlParser.State_nameContext ctx) {
        int one = 1;
    }

    @Override
    public void enterField(CqlParser.FieldContext ctx) {
        int one = 1;
    }

    @Override
    public void exitField(CqlParser.FieldContext ctx) {
        int one = 1;
    }

    @Override
    public void enterCompare_dates(CqlParser.Compare_datesContext ctx) {
        int one = 1;
    }

    @Override
    public void exitCompare_dates(CqlParser.Compare_datesContext ctx) {
        int one = 1;
    }

    @Override
    public void enterDates(CqlParser.DatesContext ctx) {
        int one = 1;
    }

    @Override
    public void exitDates(CqlParser.DatesContext ctx) {
        int one = 1;
    }

    protected String stripQuotesFromString(String input) {
        return input
                .replace("\"", "")
                .replace("'", "");
    }
}
