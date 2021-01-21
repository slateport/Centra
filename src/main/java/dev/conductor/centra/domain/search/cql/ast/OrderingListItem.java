package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.OrderTypeEnum;

public class OrderingListItem {
    //private String field;

    AbstractOrderByArgument argument;

    private OrderTypeEnum orderType = OrderTypeEnum.ASC; // default value

    public AbstractOrderByArgument getArgument() {
        return argument;
    }

    public void setArgument(AbstractOrderByArgument argument) {
        this.argument = argument;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }


    @Override
    public String toString() {
        return "OrderingListItem{" +
                "argument=" + argument +
                ", orderType=" + orderType +
                '}';
    }
}
