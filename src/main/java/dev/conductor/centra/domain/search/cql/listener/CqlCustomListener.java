package dev.conductor.centra.domain.search.cql.ast.listener;

import dev.conductor.centra.domain.search.cql.ast.*;
import dev.conductor.centra.domain.search.cql.ast.enumeration.LiteralValueTypeEnum;
import dev.conductor.centra.domain.search.cql.ast.enumeration.LogicalOperatorEnum;
import dev.conductor.centra.domain.search.cql.ast.enumeration.OperatorTypeEnum;
import dev.conductor.centra.domain.search.cql.ast.enumeration.OrderTypeEnum;
import dev.conductor.centra.domain.search.cql.cqlBaseListener;
import dev.conductor.centra.domain.search.cql.cqlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CqlCustomListener extends cqlBaseListener {

    private Stack<AbstractLogicalExpression> logicalExpressionStack = new Stack<>();

    private List<CqlStatement> statementList = new ArrayList<>();
    int statementCounter = 0;

    private Stack<Expression> expressionStack = new Stack<>();
    private Stack<AbstractRightValue> rightValueStack = new Stack<>();

    private Stack<AbstractFunctionArgument> functionArgumentStack = new Stack();

    private List<OrderingListItem> orderingListItems = new ArrayList<>();

    //****************************

    public List<CqlStatement> getStatementList() {
        return statementList;
    }

    public Stack<AbstractLogicalExpression> getLogicalExpressionStack() {
        return logicalExpressionStack;
    }

    public Stack<Expression> getExpressionStack() {
        return expressionStack;
    }

    public Stack<AbstractRightValue> getRightValueStack() {
        return rightValueStack;
    }

    public Stack<AbstractFunctionArgument> getFunctionArgumentStack() {
        return functionArgumentStack;
    }

    public List<OrderingListItem> getOrderingListItems() {
        return orderingListItems;
    }

    //******************************************
    @Override
    public void exitCql_stmt_list(cqlParser.Cql_stmt_listContext ctx) {

        if (statementList.size() < statementCounter || statementList.size() == 0) {
            throw new RuntimeException("parse error");
        }
    }

    @Override
    public void enterCql_stmt(cqlParser.Cql_stmtContext ctx) {

        statementCounter++;
    }

    @Override
    public void exitCql_stmt(cqlParser.Cql_stmtContext ctx) {

        CqlStatement cqlStatement = new CqlStatement();
        cqlStatement.setExpression(logicalExpressionStack.pop());

        cqlStatement.setOrderingList(orderingListItems);
        orderingListItems = new ArrayList<>();
        // reverse
        statementList.add(0, cqlStatement);
    }

    @Override
    public void exitAndLogicalExpression(cqlParser.AndLogicalExpressionContext ctx) {

        CombinedLogicalExpression combinedLogicalExpression = new CombinedLogicalExpression();
        combinedLogicalExpression.setLogicalOperator(LogicalOperatorEnum.AND);

        AbstractLogicalExpression logicalExpressionRight = logicalExpressionStack.pop();
        AbstractLogicalExpression logicalExpressionLeft = logicalExpressionStack.pop();

        combinedLogicalExpression.setLeft(logicalExpressionLeft);
        combinedLogicalExpression.setRight(logicalExpressionRight);

        logicalExpressionStack.push(combinedLogicalExpression);

    }

    @Override
    public void exitOrLogicalExpression(cqlParser.OrLogicalExpressionContext ctx) {

        CombinedLogicalExpression combinedLogicalExpression = new CombinedLogicalExpression();
        combinedLogicalExpression.setLogicalOperator(LogicalOperatorEnum.OR);

        AbstractLogicalExpression logicalExpressionRight = logicalExpressionStack.pop();
        AbstractLogicalExpression logicalExpressionLeft = logicalExpressionStack.pop();

        combinedLogicalExpression.setLeft(logicalExpressionLeft);
        combinedLogicalExpression.setRight(logicalExpressionRight);

        logicalExpressionStack.push(combinedLogicalExpression);

    }

    @Override
    public void exitBracedExpression(cqlParser.BracedExpressionContext ctx) {

        AbstractLogicalExpression logicalExpression = logicalExpressionStack.pop();
        logicalExpression.setBraced(true);
        logicalExpressionStack.push(logicalExpression);

    }

    @Override
    public void exitNegatedLogicalExpression(cqlParser.NegatedLogicalExpressionContext ctx) {

        AbstractLogicalExpression logicalExpression = logicalExpressionStack.pop();
        logicalExpression.setNegated(true);
        logicalExpressionStack.push(logicalExpression);

    }

    @Override
    public void enterLiteral_list(cqlParser.Literal_listContext ctx) {
        //System.out.println();

    }

    @Override
    public void exitLiteral_list(cqlParser.Literal_listContext ctx) {

        List<LiteralRightValue> abstractRightValueList = new ArrayList<>();
        while (!rightValueStack.empty()) {
            abstractRightValueList.add(0, (LiteralRightValue) rightValueStack.pop());
        }
        LiteralListRightValue literalListRightValue = new LiteralListRightValue();
        literalListRightValue.setRightValueList(abstractRightValueList);

        rightValueStack.push(literalListRightValue);
    }

    @Override
    public void exitSimpleExpression(cqlParser.SimpleExpressionContext ctx) {

        NonCombinedLogicalExpression logicalExpression = new NonCombinedLogicalExpression();

        logicalExpression.setExpression(expressionStack.pop());

        logicalExpressionStack.push(logicalExpression);
    }

    @Override
    public void exitExpr(cqlParser.ExprContext ctx) {

        Expression expression = new Expression();

        OperatorTypeEnum operatorType = null;
        String operatorString = ctx.operator().getText();

        switch (operatorString.toUpperCase()) {
            case "=":
                operatorType = OperatorTypeEnum.EQ;
                break;
            case "!=":
                operatorType = OperatorTypeEnum.NOT_EQ;
                break;
            case "IN":
                operatorType = OperatorTypeEnum.IN;
                break;
            case "NOTIN": // no space intentionally
                operatorType = OperatorTypeEnum.NOT_IN;
                break;
            case "~":
                operatorType = OperatorTypeEnum.CONTAINS;
                break;
            case "!~":
                operatorType = OperatorTypeEnum.NOT_CONTAINS;
                break;

            default:
                throw new RuntimeException("unexpected operator " + operatorString);
        }

        expression.setLeftValue(ctx.left_value().getText());
        expression.setOperatorType(operatorType);
        expression.setRightValue(rightValueStack.pop());

        expressionStack.push(expression);

    }

    @Override
    public void exitLiteral_value(cqlParser.Literal_valueContext ctx) {

        LiteralRightValue rightValue = new LiteralRightValue();
        rightValue.setValue(ctx.getText());

        if (ctx.dates() != null) {
            rightValue.setType(LiteralValueTypeEnum.DATES);
        } else if (ctx.state_name() != null) {
            rightValue.setType(LiteralValueTypeEnum.STATE_NAME);
        } else if (ctx.field() != null) {
            rightValue.setType(LiteralValueTypeEnum.FIELD);
        } else if (ctx.STRING_LITERAL() != null) {
            rightValue.setType(LiteralValueTypeEnum.STRING_LITERAL);
        } else if (ctx.IDENTIFIER() != null) {
            rightValue.setType(LiteralValueTypeEnum.IDENTIFIER);
        }

        rightValueStack.push(rightValue);

    }

    @Override
    public void enterFunction_call(cqlParser.Function_callContext ctx) {
        //System.out.println("called enterFunction_call()");
    }

    @Override
    public void exitFunction_call(cqlParser.Function_callContext ctx) {
        //System.out.println("called exitFunction_call()");

        FunctionCallRightValue rightValue = new FunctionCallRightValue();
        rightValue.setFunctionName(ctx.IDENTIFIER().getText());

        List<AbstractFunctionArgument> arguments = new ArrayList<>();
        while (!functionArgumentStack.empty()) {
            arguments.add(0, functionArgumentStack.pop());
        }

        rightValue.setArgumentList(arguments);

        rightValueStack.push(rightValue);
    }

    @Override
    public void exitArgument_list(cqlParser.Argument_listContext ctx) {
        //System.out.println("called exitArgument_list()");
    }

    @Override
    public void enterFunction_argument(cqlParser.Function_argumentContext ctx) {
        //System.out.println("called enterFunction_argument() " + ctx.getText());

        // intersection :
        /*
  function_argument :
  literal_value | function_call

  right_value:
  literal_value | literal_list | function_call | dates ;
         */

    }

    @Override
    public void exitFunction_argument(cqlParser.Function_argumentContext ctx) {
        System.out.println("called exitFunction_argument() " + ctx.getText());

        Object obj = rightValueStack.pop();
        if (!(obj instanceof AbstractFunctionArgument)) {
            throw new RuntimeException("unexpected type");
        }
        AbstractFunctionArgument abstractFunctionArgument = (AbstractFunctionArgument) obj;

        functionArgumentStack.push(abstractFunctionArgument);
    }

    @Override
    public void exitOrdering_list_item(cqlParser.Ordering_list_itemContext ctx) {
        OrderingListItem item = new OrderingListItem();
        item.setField(ctx.field().getText());


        if (ctx.order != null) {
            String orderType = ctx
                    .order
                    .getText().toUpperCase();
            if (orderType != null) {
                switch (orderType) {
                    case "ASC":
                        item.setOrderType(OrderTypeEnum.ASC);
                        break;
                    case "DESC":
                        item.setOrderType(OrderTypeEnum.DESC);
                        break;
                    default:
                        throw new RuntimeException("unexpected ordering type");
                }
            }
        }

        orderingListItems.add(item);
    }
}
