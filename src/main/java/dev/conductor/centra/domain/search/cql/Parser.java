package dev.conductor.centra.domain.search.cql;

import dev.conductor.centra.domain.search.cql.conditions.Condition;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Parser {

    public List<Condition<String>> parse(String input) {
        CqlLexer cqlLexer = new CqlLexer(CharStreams.fromString(input));
        CqlParser cqlParser = new CqlParser(new CommonTokenStream(cqlLexer));

        ParseTreeWalker walker = new ParseTreeWalker();
        CqlListenerImpl listener = new CqlListenerImpl();
        walker.walk(listener, cqlParser.parse());

        return listener.getConditions();
    }
}
