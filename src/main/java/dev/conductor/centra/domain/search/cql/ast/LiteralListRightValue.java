package dev.conductor.centra.domain.search.cql.ast;

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
}
