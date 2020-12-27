package dev.conductor.centra.domain.issue.query;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import dev.conductor.centra.domain.search.cql.ast.*;
import dev.conductor.centra.domain.search.cql.ast.enumeration.*;

import java.util.List;

public class CustomQueryBuilder {

    public static Query composeQuery(CqlStatement cqlStatement, CqlSessionContainer sessionContainer) {
        // https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/core/query/Criteria.html
        Query query = new Query();

        query.addCriteria(processLogicalExpression(cqlStatement.getExpression(), sessionContainer));

        List<OrderingListItem> orderingList = cqlStatement.getOrderingList();
        if (orderingList != null) {
            orderingList.forEach(orderingListItem -> {
                Sort.Direction direction;
                if (orderingListItem.getOrderType() == OrderTypeEnum.ASC) {
                    direction = Sort.Direction.ASC;
                } else {
                    direction = Sort.Direction.DESC;
                }

                Sort sort = Sort.by(direction, orderingListItem.getField());
                query.with(sort);
            });
        }

        return query;
    }

    // visiting AST
    private static Criteria processLogicalExpression(AbstractLogicalExpression abstractLogicalExpression, CqlSessionContainer sessionContainer) {
        if (abstractLogicalExpression instanceof NonCombinedLogicalExpression) {

            NonCombinedLogicalExpression nonCombinedLogicalExpression = (NonCombinedLogicalExpression) abstractLogicalExpression;

            boolean isNegated = nonCombinedLogicalExpression.isNegated();

            return processLogicalExpression(nonCombinedLogicalExpression.getExpression(), isNegated, sessionContainer);
        } else if (abstractLogicalExpression instanceof CombinedLogicalExpression) {

            CombinedLogicalExpression combinedLogicalExpression = (CombinedLogicalExpression) abstractLogicalExpression;

            Criteria left = processLogicalExpression(combinedLogicalExpression.getLeft(), sessionContainer);
            Criteria right = processLogicalExpression(combinedLogicalExpression.getRight(), sessionContainer);

            boolean isNegated = combinedLogicalExpression.isNegated();

            switch (combinedLogicalExpression.getLogicalOperator()) {
                case OR:
                    if (isNegated) {
                        return (left.orOperator(right)).not();
                    } else {
                        return left.orOperator(right);
                    }
                case AND:
                    if (isNegated) {
                        return (left.andOperator(right)).not();
                    } else {
                        return left.andOperator(right);
                    }
                default:
                    throw new IllegalStateException("unexpected logical operator (connector)");
            }

        } else {
            throw new IllegalStateException("unknown type of logical expression");
        }
    }

    private static Criteria processLogicalExpression(Expression expression, boolean isNegated, CqlSessionContainer sessionContainer) {

        String leftValue = expression.getLeftValue();
        OperatorTypeEnum operatorType = expression.getOperatorType();
        AbstractRightValue rightValue = expression.getRightValue();

        boolean rightValueIsLiteral = (rightValue instanceof LiteralRightValue);
        boolean rightValueIsLiteralList = (rightValue instanceof LiteralListRightValue);
        boolean rightValueIsFunctionCall = (rightValue instanceof FunctionCallRightValue);

        Criteria criteria;
        Object evaluatedRightValue = "";
        String regex = "";

        switch (operatorType) {
            case EQ:

                if (rightValueIsLiteralList) {
                    throw new IllegalStateException("EQ operator shouldn't be used against list");
                }

                if (rightValueIsFunctionCall) {
                    evaluatedRightValue = sessionContainer.processFunctionStatement((FunctionCallRightValue) rightValue);
                } else {
                    evaluatedRightValue = rightValue.getRightValue();
                }

                if (isNegated) {
                    criteria = Criteria.where(leftValue).ne(evaluatedRightValue);
                } else {
                    criteria = Criteria.where(leftValue).is(evaluatedRightValue);
                }

                break;

            case NOT_EQ:

                if (rightValueIsLiteralList) {
                    throw new IllegalStateException("EQ operator shouldn't be used against list");
                }

                if (rightValueIsFunctionCall) {
                    evaluatedRightValue = sessionContainer.processFunctionStatement((FunctionCallRightValue) rightValue);
                } else {
                    evaluatedRightValue = rightValue.getRightValue();
                }

                if (isNegated) {
                    criteria = Criteria.where(leftValue).is(evaluatedRightValue);
                } else {
                    criteria = Criteria.where(leftValue).ne(evaluatedRightValue);
                }

                break;

            case IN:

                if (!rightValueIsLiteralList) {
                    throw new IllegalStateException("IN operator should be used against list");
                }

                if (rightValueIsFunctionCall) {
                    evaluatedRightValue = sessionContainer.processFunctionStatement((FunctionCallRightValue) rightValue);
                } else {
                    evaluatedRightValue = rightValue.getRightValue();
                }

                if (isNegated) {
                    criteria = Criteria.where(leftValue).nin(evaluatedRightValue);
                } else {
                    criteria = Criteria.where(leftValue).in(evaluatedRightValue);
                }

                break;

            case NOT_IN:

                if (!rightValueIsLiteralList) {
                    throw new IllegalStateException("NOT IN operator should be used against list");
                }

                if (rightValueIsFunctionCall) {
                    evaluatedRightValue = sessionContainer.processFunctionStatement((FunctionCallRightValue) rightValue);
                } else {
                    evaluatedRightValue = rightValue.getRightValue();
                }

                if (isNegated) {
                    criteria = Criteria.where(leftValue).in(evaluatedRightValue);
                } else {
                    criteria = Criteria.where(leftValue).nin(evaluatedRightValue);
                }

                break;

            case CONTAINS:
                // db.users.findOne({"username" : {$regex : ".*son.*"}}); ?

                if (!rightValueIsLiteral) {
                    throw new IllegalStateException("CONTAINS operator should be used against literal string value");
                }

                regex = "/" + rightValue.getRightValue() + "/";
                //regex = (String) rightValue.getRightValue();
                if (isNegated) {
                    criteria = Criteria.where(leftValue).regex(regex, "i").not();
                } else {
                    criteria = Criteria.where(leftValue).regex(regex, "i");
                }

                break;

            case NOT_CONTAINS:
                // db.employees.find({"name": { $not: /John/ }})
                // db.employees.find({ "name": $not:{$regex: /John/ }}})

                if (!rightValueIsLiteral) {
                    throw new IllegalStateException("NOT CONTAINS operator should be used against literal string value");
                }

                regex = "/" + rightValue.getRightValue() + "/";
                //regex = (String) rightValue.getRightValue();
                if (isNegated) {
                    criteria = Criteria.where(leftValue).regex(regex, "i");
                } else {
                    // https://docs.mongodb.com/manual/reference/operator/query/regex/
                    criteria = Criteria.where(leftValue).regex(regex, "i").not();
                }
                break;

            default:
                throw new IllegalStateException("unknown type of operator");
        }

        return criteria;
    }
}
