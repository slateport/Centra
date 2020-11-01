package dev.conductor.dataservices.cql;

public class Limit {

    private final int limit;

    public Limit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
