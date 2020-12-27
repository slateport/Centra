package dev.conductor.centra.domain.issue.query;

import dev.conductor.centra.domain.search.cql.ast.CqlStatement;
import dev.conductor.centra.domain.search.cql.cqlLexer;
import dev.conductor.centra.domain.search.cql.cqlParser;
import dev.conductor.centra.domain.search.cql.ast.listener.CqlCustomListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomQueryBuilderTest {

    private CqlSessionContainer sessionContainer;

    @Before
    public void setup() {
        sessionContainer = new CqlSessionContainer();
        sessionContainer.setUsername("dk2k");
    }

    private CqlCustomListener setup(String sample) {
        CodePointCharStream input = CharStreams.fromString(sample);
        cqlLexer lexer = new cqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        cqlParser parser = new cqlParser(tokens);
        ParseTree tree = parser.cql_stmt_list();

        ParseTreeWalker walker = new ParseTreeWalker();
        CqlCustomListener listener = new CqlCustomListener();
        walker.walk(listener, tree);
        return  listener;
    }

    @Test
    public void testNotEqual() {
        String s = "issueType != Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$ne\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testNegationCase1() {
        String s = "NOT issueType = Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$ne\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testNegationCase2() {
        String s = "NOT issueType != Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : \"Bug\"}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithOrCase1() {
        String s = "projectKey = DEMO OR projectKey = 1111";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"projectKey\" : \"DEMO\", \"$or\" : [{ \"projectKey\" : \"1111\"}]}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithAndOrCase1() {

        String s = "NOT (projectKey = DEMO or projectKey = Bug)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // N.B. $not in the query can be seen in debug only
        Assert.assertEquals("Query: { \"projectKey\" : \"DEMO\", \"$or\" : [{ \"projectKey\" : \"Bug\"}]}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithAndOrCase2() {

        String s = "NOT (projectKey = DEMO AND projectKey = Bug)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // N.B. $not in the query can be seen in debug only
        Assert.assertEquals("Query: { \"projectKey\" : \"DEMO\", \"$and\" : [{ \"projectKey\" : \"Bug\"}]}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testNegatedCompositionCase1() {

        String s = "(projectKey = DEMO or projectKey = Bug) and assignee = shamil";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"projectKey\" : \"DEMO\", \"$or\" : [{ \"projectKey\" : \"Bug\"}], \"$and\" : [{ \"assignee\" : \"shamil\"}]}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testCurrentUserFunction() {

        String s = "assignee = currentUser() ";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"assignee\" : \"dk2k\"}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testCurrentDateFunction() {

        String s = " createdDate = currentDate() ";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"createdDate\" : \"27-12-2020\"}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorIn() {
        String s = "issueType IN (Bug,Story,Task)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$in\" : [ [ \"Bug\", \"Story\", \"Task\" ] ] } }, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorLike() {
        String s = "Text ~ \"foo\"";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"Text\" : { \"$regularExpression\" : { \"pattern\" : \"/\\\"foo\\\"/\", \"options\" : \"i\"}}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorLikeNegated() {
        String s = "NOT Text ~ \"foo\"";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // N.B. $not in the query can be seen in debug only
        // https://jira.mongodb.org/browse/JAVA-548
        Assert.assertEquals("Query: { \"Text\" : { \"$regularExpression\" : { \"pattern\" : \"/\\\"foo\\\"/\", \"options\" : \"i\"}}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorNotLikeCase1() {
        String s = "Text !~ \"foo\"";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // N.B. $not in the query can be seen in debug only
        // https://jira.mongodb.org/browse/JAVA-548
        Assert.assertEquals("Query: { \"Text\" : { \"$regularExpression\" : { \"pattern\" : \"/\\\"foo\\\"/\", \"options\" : \"i\"}}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOrderByCase3() {
        String s = "issueType NOT IN (Bug,Story,Task) order by text desc order by description order by projectKey Desc";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$nin\" : [ [ \"Bug\", \"Story\", \"Task\" ] ] } }, Fields: {}, Sort: { \"text\" : -1, \"description\" : 1, \"projectKey\" : -1}",
                query.toString());

        System.out.println("******************************");
    }
}
