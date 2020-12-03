package dev.conductor.centra.domain.search.cql;

import dev.conductor.centra.domain.search.cql.conditions.*;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CqlListenerImpl extends CqlBaseListener {

    private final List<Condition<String>> conditions = new ArrayList<>();

    private Condition<String> currentCondition;

    public List<Condition<String>> getConditions() {
        return conditions;
    }

    @Override
    public void enterExpr(CqlParser.ExprContext ctx) {
        switch(stripQuotesFromString(ctx.getStart().getText())) {
            case "projectKey":
                this.currentCondition = new ProjectKeys();
                this.currentCondition.addValue(ctx.getStop().getText());
                break;

            case "status":
                this.currentCondition = new IssueStatus();
                this.currentCondition.addValue(ctx.getStop().getText());
                break;

            case "assignee":
                this.currentCondition = new Assignee();
                this.currentCondition.addValue(ctx.getStop().getText());
                break;

            case "text":
            case "description":
                this.currentCondition = new Description();
                this.currentCondition.addValue(ctx.getStop().getText());
                break;

            case "label":
            case "labels":
                this.currentCondition = new Labels();
                this.currentCondition.addValue(ctx.getStop().getText());
                break;

            case "createdByUserId":
            case "reporter":
                this.currentCondition = new Reporter();
                this.currentCondition.addValue(ctx.getStop().getText());
                break;

            default:
                throw new IllegalArgumentException("Unknown parameter " + stripQuotesFromString(ctx.getStart().getText()));
        }
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

            case "IN":
                operator = Operator.IN;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + ctx.getStop().getText());

        }

        this.currentCondition.setOperator(operator);
    }

    @Override public void enterLiteral_list(CqlParser.Literal_listContext ctx) {
        List<String> items = ctx.literal_value().stream().map(RuleContext::getText).collect(Collectors.toList());
        this.currentCondition.setValue(items);
    }
    protected String stripQuotesFromString(String input) {
        return input
                .replace("\"", "")
                .replace("'", "");
    }
}
