package dev.conductor.centra.domain.search.cql.ast;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallRightValue implements AbstractRightValue, AbstractFunctionArgument {
    private String functionName;
    private List<AbstractFunctionArgument> argumentList = new ArrayList<>();

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<AbstractFunctionArgument> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<AbstractFunctionArgument> argumentList) {
        this.argumentList = argumentList;
    }

    public void addArgument(AbstractFunctionArgument argument) {
        this.argumentList.add(argument);
    }

    @Override
    public String toString() {
        return "FunctionCallRightValue{" +
                "functionName='" + functionName + '\'' +
                ", argumentList=" + argumentList +
                '}';
    }
}
