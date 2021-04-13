package dev.conductor.centra.domain.search.cql.ast;

import java.util.ArrayList;
import java.util.List;

public class CqlStatement {
    private AbstractLogicalExpression expression;
    private List<OrderingListItem> orderingList = new ArrayList<>();

    public List<OrderingListItem> getOrderingList() {
        return orderingList;
    }

    public void setOrderingList(List<OrderingListItem> orderingList) {
        this.orderingList = orderingList;
    }

    public AbstractLogicalExpression getExpression() {
        return expression;
    }

    public void setExpression(AbstractLogicalExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "CqlStatement{" +
                "expression=" + expression +
                '}';
    }

    public String prettyPrint() {
        return "CqlStatement{" +
                "\nexpression=" + expression.prettyPrint(0) +
                "\n}";
    }
}
