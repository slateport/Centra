package dev.conductor.centra.domain.search.cql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class Parser {

    public CqlQuery parse(String input) {
        CqlLexer cqlLexer = new CqlLexer(CharStreams.fromString(input));
        CqlParser cqlParser = new CqlParser(new CommonTokenStream(cqlLexer));

        ParseTreeWalker walker = new ParseTreeWalker();
        CqlListenerImpl listener = new CqlListenerImpl();
        walker.walk(listener, cqlParser.parse());

        return new CqlQuery(
                new SelectClause(
                    new ArrayList<>(Collections.singletonList("id"))
                ),
                new WhereClause(
                        listener.getConditions()
                ),
                new Limit(100)
        );
    }
}
