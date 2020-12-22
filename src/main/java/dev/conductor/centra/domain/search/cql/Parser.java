package dev.conductor.centra.domain.search.cql;

import dev.conductor.centra.domain.search.cql.conditions.Condition;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Parser {

    public List<Condition> parse(String input) {
        cqlParser cqlParser = new cqlParser(new CommonTokenStream(new cqlLexer(CharStreams.fromString(input))));

        ParseTreeWalker walker = new ParseTreeWalker();
        cqlListenerImpl listener = new cqlListenerImpl();
        walker.walk(listener, cqlParser.parse());

        return listener.getConditions();
    }
}
