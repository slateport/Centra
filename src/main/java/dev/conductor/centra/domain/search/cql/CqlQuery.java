package dev.conductor.centra.domain.search.cql;


public class CqlQuery {
    private final SelectClause select;
    private final WhereClause where;
    private final Limit limit;

    public CqlQuery(SelectClause select, WhereClause where, Limit limit) {
        this.select = select;
        this.where = where;
        this.limit = limit;
    }

    public SelectClause getSelect() {
        return select;
    }

    public WhereClause getWhere() {
        return where;
    }

    public Limit getLimit() {
        return limit;
    }
}
