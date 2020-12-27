package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.LiteralValueTypeEnum;

public class LiteralRightValue implements AbstractRightValue, AbstractFunctionArgument {
    private String value;
    private LiteralValueTypeEnum type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        //throw new NotImplementedException("");
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
                return value;
            default:
                throw new IllegalStateException();
        }
    }

}
