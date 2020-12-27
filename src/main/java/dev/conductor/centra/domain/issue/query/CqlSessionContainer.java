package dev.conductor.centra.domain.issue.query;

import dev.conductor.centra.domain.search.cql.ast.FunctionCallRightValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// to be @Component or at least a singleton
public class CqlSessionContainer {

    private String username = "hardcoded_value"; // actual value is to be set in the setter below

    public void setUsername(String username) {
        this.username = username;
    }

    Object processFunctionStatement(FunctionCallRightValue functionCall) {

        switch (functionCall.getFunctionName().toLowerCase()) { // case insensitive

            case "currentuser":
                return username;

            case "currentdate":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.now().format(formatter);

            default:
                throw new IllegalStateException("unknown function");
        }

    }
}
