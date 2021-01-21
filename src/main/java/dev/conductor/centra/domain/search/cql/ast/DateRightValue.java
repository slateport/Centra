package dev.conductor.centra.domain.search.cql.ast;

import java.util.Date;

public class DateRightValue implements AbstractRightValue {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Object getRightValue() {
        return date;
    }
}
