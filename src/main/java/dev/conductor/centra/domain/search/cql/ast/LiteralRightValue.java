package dev.conductor.centra.domain.search.cql.ast;

public class LiteralRightValue implements AbstractRightValue, AbstractFunctionArgument {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LiteralRightValue{" +
                "value='" + value + '\'' +
                '}';
    }
}
