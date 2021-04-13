package dev.conductor.centra.domain.search.cql.ast;

import dev.conductor.centra.domain.search.cql.ast.enumeration.LiteralValueTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class LiteralListRightValue implements AbstractRightValue {
    private List<LiteralRightValue> rightValueList = new ArrayList<>();

    public List<LiteralRightValue> getRightValueList() {
        return rightValueList;
    }

    public void setRightValueList(List<LiteralRightValue> rightValueList) {
        this.rightValueList = rightValueList;
    }

    @Override
    public String toString() {
        return "LiteralListRightValue{" +
                "rightValueList=" + rightValueList +
                '}';
    }

    @Override
    public Object getRightValue() {
        List<Object> objects = new ArrayList<>();
        rightValueList.forEach(rightValue -> {
            LiteralValueTypeEnum type = rightValue.getType();

            switch (type) {
                case STRING_LITERAL:
                    String value = rightValue.getValue().toString();
                    objects.add(value.substring(1, value.length() - 1));
                    break;
                case IDENTIFIER:
                    objects.add(rightValue.getValue());
                    break;
                case FIELD:
                    objects.add(rightValue.getValue());
                    break;
                case STATE_NAME:
                    objects.add(rightValue.getValue());
                    break;
                case DATES:
                    objects.add(rightValue.getValue());
                    break;
                case KEYWORD:
                    objects.add(rightValue.getValue());
                    break;
                case NUMBER_LITERAL:
                    objects.add(rightValue.getValue());
                    break;
                default:
                    throw new IllegalStateException(type.toString());
            }

        });
        return objects.toArray();
    }
}
