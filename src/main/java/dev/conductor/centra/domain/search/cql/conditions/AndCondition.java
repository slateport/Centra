package dev.conductor.centra.domain.search.cql.conditions;

import dev.conductor.centra.domain.search.cql.Operator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AndCondition {

    private String rhs;
    private Operator operator;
    private Object lhs;


    public AndCondition(String rhs, Operator operator, String lhs) {
        this.rhs = rhs;
        this.operator = operator;
        this.lhs = lhs;
    }
}
