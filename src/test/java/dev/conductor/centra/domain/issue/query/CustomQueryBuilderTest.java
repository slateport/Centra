package dev.conductor.centra.domain.issue.query;

import dev.conductor.centra.domain.search.cql.ast.CqlStatement;
import dev.conductor.centra.domain.search.cql.cqlLexer;
import dev.conductor.centra.domain.search.cql.cqlParser;
import dev.conductor.centra.domain.search.cql.listener.CqlCustomListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    private CqlCustomListener setup(String sample, LocalDateTime ldt) {
        CodePointCharStream input = CharStreams.fromString(sample);
        cqlLexer lexer = new cqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        cqlParser parser = new cqlParser(tokens);
        ParseTree tree = parser.cql_stmt_list();

        ParseTreeWalker walker = new ParseTreeWalker();
        CqlCustomListener listener = new CqlCustomListener();
        listener.setNow(ldt);
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expected = LocalDate.now().format(formatter);

        Assert.assertEquals("Query: { \"createdDate\" : \""+ expected +"\"}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorInCase1() {
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
    public void testOperatorInCase2() {
        String s = "issueType IN (\"Bug\", \"Story\", \"Task\")";
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
    public void testOperatorInCase3() {
        String s = "priority in (Blocker, Critical)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"priority\" : { \"$in\" : [ [ \"Blocker\", \"Critical\" ] ] } }, Fields: {}, Sort: {}",
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

        Assert.assertEquals("Query: { \"Text\" : { \"$regularExpression\" : { \"pattern\" : \"/foo/\", \"options\" : \"i\"}}}, Fields: {}, Sort: {}",
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
        Assert.assertEquals("Query: { \"Text\" : { \"$regularExpression\" : { \"pattern\" : \"/foo/\", \"options\" : \"i\"}}}, Fields: {}, Sort: {}",
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
        Assert.assertEquals("Query: { \"Text\" : { \"$regularExpression\" : { \"pattern\" : \"/foo/\", \"options\" : \"i\"}}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testOrderByCase3() {
        String s = "issueType NOT IN (Bug,Story,Task) order by text desc, description, projectKey Desc";
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

    @Test
    public void testIsCase1() {
        String s = "description Is NULL";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : { \"$exists\" : false}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsCase2() {
        String s = "NOT description Is NULL";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : { \"$exists\" : true}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsCase3() {
        String s = "description Is empty";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : \"\"}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsCase4() {
        String s = "NOT description Is empty";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : { \"$ne\" : \"\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsNotCase1() {
        String s = "description Is not NULL";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : { \"$exists\" : true}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsNotCase2() {
        String s = "NOT description Is not NULL";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : { \"$exists\" : false}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsNotCase3() {
        String s = "description Is not empty";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : { \"$ne\" : \"\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testIsNotCase4() {
        String s = "NOT description Is not empty";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);


        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"description\" : \"\"}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testGreaterCase1() {
        String s = "issueType > Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testGreaterCase2() {
        String s = "NOT issueType > Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$lte\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testLessCase1() {
        String s = "issueType < Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$lt\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testLessCase2() {
        String s = "NOT issueType < Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$gte\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testGreaterOrEqualCase1() {
        String s = "issueType >= Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$gte\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testGreaterOrEqualCase2() {
        String s = "NOT issueType >= Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$lt\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testLessOrEqualCase1() {
        String s = "issueType <= Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$lte\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testLessOrEqualCase2() {
        String s = "NOT issueType <= Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"Bug\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testNumberCase1() {
        String s = "NOT issueType <= -100";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"-100\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testNumberCase2() {
        String s = "NOT issueType <= +100";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
                query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase1() {
        String s = "createdDate <= +10d";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        now = now.plusDays(10l);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"createdDate\" : { \"$lte\" : { \"$date\" : \"2020-10-30T02:30:00Z\"}}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase2() {
        String s = "createdDate <= +1w -2d and issueType >= 100";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase3() {
        String s = "createdDate <= 50m";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase4() {
        String s = "createdDate < 2021-01-28";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase5() {
        String s = "createdDate < 2021-01-28 16:00";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase6() {
        String s = "createdDate < 2021-01-28 16:00 and issueType is null";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase7() {
        String s = "createdDate < '2021-01-28'";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase8() {
        String s = "createdDate < '2021-01-28 16:00'";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase9() {
        String s = "createdDate < \"2021-01-28\"";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }

    @Test
    public void testDateCase10() {
        String s = "createdDate < \"2021-01-28 16:00\"";

        LocalDateTime now = LocalDateTime.of(2020, 10, 20, 5, 30);
        CqlCustomListener listener = setup(s, now);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);

        Query query = CustomQueryBuilder.composeQuery(statement, sessionContainer);
        System.out.println(query);

        // depends on the local time zone
        //Assert.assertEquals("Query: { \"issueType\" : { \"$gt\" : \"100\"}}, Fields: {}, Sort: {}",
        //        query.toString());

        System.out.println("******************************");
    }
}
