package dev.conductor.centra.domain.search.cql.ast;

public abstract class AbstractLogicalExpression {

    protected final String INDENT = "    ";

    private boolean negated;
    private boolean braced;

    public boolean isNegated() {
        return negated;
    }

    public void setNegated(boolean negated) {
        this.negated = negated;
    }

    public boolean isBraced() {
        return braced;
    }

    public void setBraced(boolean braced) {
        this.braced = braced;
    }

    @Override
    public String toString() {
        return "AbstractLogicalExpression{" +
                "negated=" + negated +
                ", braced=" + braced +
                '}';
    }

    public abstract String prettyPrint(int level);
}
