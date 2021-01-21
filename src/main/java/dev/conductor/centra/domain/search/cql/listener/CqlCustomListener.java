package dev.conductor.centra.domain.search.cql.listener;

import dev.conductor.centra.domain.search.cql.ast.*;
import dev.conductor.centra.domain.search.cql.ast.enumeration.LiteralValueTypeEnum;
import dev.conductor.centra.domain.search.cql.ast.enumeration.LogicalOperatorEnum;
import dev.conductor.centra.domain.search.cql.ast.enumeration.OperatorTypeEnum;
import dev.conductor.centra.domain.search.cql.ast.enumeration.OrderTypeEnum;
import dev.conductor.centra.domain.search.cql.cqlBaseListener;
import dev.conductor.centra.domain.search.cql.cqlParser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CqlCustomListener extends cqlBaseListener {

    // going to inject it so that I can unit test
    private LocalDateTime now = LocalDateTime.now();

    private Stack<AbstractLogicalExpression> logicalExpressionStack = new Stack<>();

    private List<CqlStatement> statementList = new ArrayList<>();
    int statementCounter = 0;

    private Stack<Expression> expressionStack = new Stack<>();
    private Stack<AbstractRightValue> rightValueStack = new Stack<>();

    private Stack<AbstractFunctionArgument> functionArgumentStack = new Stack();
    private Stack<DateRightValue> dateRightValueStack = new Stack<>();

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

    public void setNow(LocalDateTime now) {
        this.now = now;
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

        if (ctx.operator() != null) {
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
                case ">=":
                    operatorType = OperatorTypeEnum.GT_EQ;
                    break;
                case ">":
                    operatorType = OperatorTypeEnum.GT;
                    break;
                case "<=":
                    operatorType = OperatorTypeEnum.LT_EQ;
                    break;
                case "<":
                    operatorType = OperatorTypeEnum.LT;
                    break;

                default:
                    throw new RuntimeException("unexpected operator " + operatorString);
            }

            expression.setLeftValue(ctx.left_value().getText());
            expression.setOperatorType(operatorType);
            expression.setRightValue(rightValueStack.pop());
        } else if (ctx.is_operator() != null) {
            String isOperatorString = ctx.is_operator().getText();
            switch (isOperatorString.toUpperCase()) {
                case "IS":
                    operatorType = OperatorTypeEnum.IS;
                    break;
                case "ISNOT":
                    operatorType = OperatorTypeEnum.IS_NOT;
                    break;
                default:
                    throw new RuntimeException("unexpected operator " + isOperatorString);
            }

            expression.setLeftValue(ctx.left_value().getText());
            expression.setOperatorType(operatorType);
            expression.setRightValue(new KeyWordRightValue(ctx.operand.getText()));
        }


        expressionStack.push(expression);

    }

    @Override
    public void exitLiteral_value(cqlParser.Literal_valueContext ctx) {

        LiteralRightValue rightValue = new LiteralRightValue();
        rightValue.setValue(ctx.getText());

        if (ctx.dates() != null) {
            //System.out.println(dateRightValueStack.empty());
            DateRightValue dateRightValue = dateRightValueStack.pop();

            rightValue.setDate(dateRightValue.getDate());
            rightValue.setType(LiteralValueTypeEnum.DATES);
        } else if (ctx.state_name() != null) {
            rightValue.setType(LiteralValueTypeEnum.STATE_NAME);
        } else if (ctx.field() != null) {
            rightValue.setType(LiteralValueTypeEnum.FIELD);
        } else if (ctx.STRING_LITERAL() != null) {
            rightValue.setType(LiteralValueTypeEnum.STRING_LITERAL);
        } else if (ctx.IDENTIFIER() != null) {
            rightValue.setType(LiteralValueTypeEnum.IDENTIFIER);
        } else if (ctx.number() != null) {
            rightValue.setType(LiteralValueTypeEnum.NUMBER_LITERAL);
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
    public void exitDateType1(cqlParser.DateType1Context ctx) {
        DateRightValue dateRightValue = new DateRightValue();

        AtomicReference<LocalDateTime> ldtRef = new AtomicReference<>(now);

        List<cqlParser.Number_and_termContext> number_and_terms = ctx.number_and_term();
        if (number_and_terms != null) {
            //System.out.println(number_and_terms.size());
            number_and_terms.stream().forEach(nat -> {

                int number = Integer.valueOf(nat.number().getText());
                String unit = nat.unit.getText();

                LocalDateTime ldt = ldtRef.get();

                switch (unit) {
                    case "d":
                        ldt = ldt.plusDays(number);
                        break;
                    case "w":
                        ldt = ldt.plusWeeks(number);
                        break;
                    case "M":
                        ldt = ldt.plusMonths(number);
                        break;
                    case "y":
                        ldt = ldt.plusYears(number);
                        break;
                    case "h":
                        ldt = ldt.plusHours(number);
                        break;
                    case "m":
                        ldt = ldt.plusMinutes(number);
                        break;
                    default:
                        throw new IllegalStateException("unexpected unit");
                }
                ldtRef.set(ldt);
            });

        }
        dateRightValue.setDate(Date.from(ldtRef.get().atZone(ZoneId.systemDefault()).toInstant()));
        dateRightValueStack.push(dateRightValue);
    }

    @Override
    public void exitDateType2(cqlParser.DateType2Context ctx) {
        DateRightValue dateRightValue = new DateRightValue();

        String dateString = ctx.DATETIME_LITERAL().getText();

        dateRightValue.setDate(processDateString(dateString));

        dateRightValueStack.push(dateRightValue);
    }

    @Override
    public void exitDateType3(cqlParser.DateType3Context ctx) {
        DateRightValue dateRightValue = new DateRightValue();

        String dateString = ctx.DATETIME_LITERAL_QUOTED_TYPE1().getText();
        dateString = dateString.substring(1, dateString.length()-1);

        dateRightValue.setDate(processDateString(dateString));

        dateRightValueStack.push(dateRightValue);
    }

    @Override
    public void exitDateType4(cqlParser.DateType4Context ctx) {
        DateRightValue dateRightValue = new DateRightValue();

        String dateString = ctx.DATETIME_LITERAL_QUOTED_TYPE2().getText();
        dateString = dateString.substring(1, dateString.length()-1);

        dateRightValue.setDate(processDateString(dateString));

        dateRightValueStack.push(dateRightValue);
    }

    private Date processDateString(String dateString) {
        Date date = null;
        int length = dateString.length();
        if (length == 10) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf1.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (length == 16) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            try {
                date = sdf1.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException();
        }
        return date;
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
        //System.out.println("called exitFunction_argument() " + ctx.getText());

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
        //cqlParser.Order_by_argumentContext argumentContext = ctx.order_by_argument();
        //System.out.println(argumentContext.getText());
        //item.setField(ctx..getText());

        AbstractOrderByArgument argument = null;

        if (!functionArgumentStack.empty()) {
            AbstractFunctionArgument abstractFunctionArgument = functionArgumentStack.pop();
            if (abstractFunctionArgument instanceof FunctionCallRightValue) {
                argument = (FunctionCallRightValue) abstractFunctionArgument;
            } else {
                throw new IllegalStateException("order list item of unexpected type");
            }
        } else {
            argument = new FieldOrderByArgument();
            ((FieldOrderByArgument) argument).setField(ctx.order_by_argument().getText());
        }

        item.setArgument(argument);

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
