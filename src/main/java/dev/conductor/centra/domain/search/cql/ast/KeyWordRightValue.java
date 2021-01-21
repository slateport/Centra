package dev.conductor.centra.domain.search.cql.ast;

public class KeyWordRightValue implements AbstractRightValue {

    private String keyword;

    public KeyWordRightValue(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Object getRightValue() {
        return keyword;
    }

    @Override
    public String toString() {
        return "KeyWordRightValue{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
