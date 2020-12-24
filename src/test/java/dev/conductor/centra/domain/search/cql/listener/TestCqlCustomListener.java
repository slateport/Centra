package dev.conductor.centra.domain.search.cql.ast.listener;

import dev.conductor.centra.domain.search.cql.ast.CqlStatement;
import dev.conductor.centra.domain.search.cql.ast.OrderingListItem;
import dev.conductor.centra.domain.search.cql.cqlLexer;
import dev.conductor.centra.domain.search.cql.cqlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCqlCustomListener {

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
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());
        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testNegationCase1() {
        String s = "NOT issueType = Bug";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithAndCase1() {

        String s = "issueType = Bug AND assignee = currentUser()";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());
        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithAndCase2() {
        String s = "issueType = Bug AND assignee = currentUser(123)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());
        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithAndCase3() {
        String s = "issueType = Bug AND assignee = currentUser(true, 1556)"; // added arg list
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());
        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithOrCase1() {
        String s = "projectKey = DEMO OR projectKey = 1111";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/
        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testCompositionWithAndOrCase1() {

        String s = "(projectKey = DEMO or projectKey = Bug) and assignee = shamil";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);
        System.out.println(statement.prettyPrint());

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());
        System.out.println(">>> " + listener.getRightValueStack().size());
        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    // no braces. Operator AND is supposed to have higher priority
    @Test
    public void testCompositionWithAndOrCase2() {

        String s = "projectKey = DEMO or projectKey = Bug and assignee = shamil";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        //System.out.println(statement);
        System.out.println(statement.prettyPrint());

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());

        System.out.println(">>> " + listener.getLogicalExpressionStack().size());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        System.out.println(">>> " + listener.getRightValueStack().size());
        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorIn() {
        String s = "issueType IN (Bug,Story,Task)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorNotIn() {
        String s = "issueType NOT IN (Bug,Story,Task)";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorLike() {
        String s = "Text ~ \"foo\"";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorNotLikeCase1() {
        String s = "Text !~ \"foo\"";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOperatorNotLikeCase2() {
        String s = "description !~ \"foo\"";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testIncorrectField() {
        String s = "Text____ !~ \"foo\""; // parser doesn't expect field Text____
        try {
            CqlCustomListener listener = setup(s);
            Assert.fail("no exception");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "parse error");
        }

        System.out.println("******************************");
    }

    @Test
    public void testOrderByCase1() {
        String s = "issueType NOT IN (Bug,Story,Task) order by text";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOrderByCase2() {
        String s = "issueType NOT IN (Bug,Story,Task) order by text asc";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        System.out.println("******************************");
    }

    @Test
    public void testOrderByCase3() {
        String s = "issueType NOT IN (Bug,Story,Task) order by text desc order by description order by projectKey Desc";
        CqlCustomListener listener = setup(s);

        List<CqlStatement> statementList = listener.getStatementList();
        Assert.assertTrue(statementList.size() == 1);
        CqlStatement statement = statementList.get(0);
        System.out.println(statement);

        Assert.assertTrue(listener.getExpressionStack().empty());
        Assert.assertTrue(listener.getFunctionArgumentStack().empty());
        Assert.assertTrue(listener.getLogicalExpressionStack().empty());

        /*while(!listener.getRightValueStack().empty()) {
            System.out.println(listener.getRightValueStack().pop());
        }*/

        Assert.assertTrue(listener.getRightValueStack().empty());

        List<OrderingListItem> orderingListItems = statement.getOrderingList();
        Assert.assertEquals(orderingListItems.size(), 3);

        orderingListItems.forEach(orderingListItem -> {
            System.out.println(orderingListItem);
        });

        System.out.println("******************************");
    }
}
