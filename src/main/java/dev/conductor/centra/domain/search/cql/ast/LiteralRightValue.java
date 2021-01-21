package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.LiteralValueTypeEnum;

import java.util.Date;

public class LiteralRightValue implements AbstractRightValue, AbstractFunctionArgument {
    private String value;
    private Date date;

    private LiteralValueTypeEnum type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LiteralValueTypeEnum getType() {
        return type;
    }

    public void setType(LiteralValueTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LiteralRightValue{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public Object getRightValue() {

        switch (type) {
            case STRING_LITERAL:
                return value.substring(1, value.length() - 1);
            case IDENTIFIER:
                return value;
            case FIELD:
                return value;
            case STATE_NAME:
                return value;
            case DATES:
                return date;
            case NUMBER_LITERAL:
                String possibleSign = value.substring(0,1);
                if (possibleSign.equals("-")) {
                    // negative value
                    return value;
                } else if (possibleSign.equals("+")) {
                    return value.substring(1);
                } else {
                    return value;
                }

            default:
                throw new IllegalStateException();
        }
    }

}
