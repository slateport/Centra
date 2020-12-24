package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.OrderTypeEnum;

public class OrderingListItem {
    private String field;
    private OrderTypeEnum orderType = OrderTypeEnum.ASC; // default value

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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
                "field='" + field + '\'' +
                ", orderType=" + orderType +
                '}';
    }
}
