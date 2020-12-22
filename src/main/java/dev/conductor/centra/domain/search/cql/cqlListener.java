// Generated from D:\soft\free\centra\Centra\tools\bat\..\..\src\main\antlr4\dev\conductor\cql\cql.g4 by ANTLR 4.9
package dev.conductor.centra.domain.search.cql;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link cqlParser}.
 */
public interface cqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link cqlParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(cqlParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(cqlParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#cql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterCql_stmt_list(cqlParser.Cql_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#cql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitCql_stmt_list(cqlParser.Cql_stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#cql_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCql_stmt(cqlParser.Cql_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#cql_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCql_stmt(cqlParser.Cql_stmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterAndLogicalExpression(cqlParser.AndLogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitAndLogicalExpression(cqlParser.AndLogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterOrLogicalExpression(cqlParser.OrLogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitOrLogicalExpression(cqlParser.OrLogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracedExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterBracedExpression(cqlParser.BracedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracedExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitBracedExpression(cqlParser.BracedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegatedLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterNegatedLogicalExpression(cqlParser.NegatedLogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegatedLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitNegatedLogicalExpression(cqlParser.NegatedLogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(cqlParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(cqlParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(cqlParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(cqlParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#right_value}.
	 * @param ctx the parse tree
	 */
	void enterRight_value(cqlParser.Right_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#right_value}.
	 * @param ctx the parse tree
	 */
	void exitRight_value(cqlParser.Right_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#left_value}.
	 * @param ctx the parse tree
	 */
	void enterLeft_value(cqlParser.Left_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#left_value}.
	 * @param ctx the parse tree
	 */
	void exitLeft_value(cqlParser.Left_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_term(cqlParser.Ordering_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_term(cqlParser.Ordering_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#ordering_list}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_list(cqlParser.Ordering_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#ordering_list}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_list(cqlParser.Ordering_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#ordering_list_item}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_list_item(cqlParser.Ordering_list_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#ordering_list_item}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_list_item(cqlParser.Ordering_list_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#order_by}.
	 * @param ctx the parse tree
	 */
	void enterOrder_by(cqlParser.Order_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#order_by}.
	 * @param ctx the parse tree
	 */
	void exitOrder_by(cqlParser.Order_byContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(cqlParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(cqlParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(cqlParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(cqlParser.Literal_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(cqlParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(cqlParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list(cqlParser.Argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list(cqlParser.Argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#function_argument}.
	 * @param ctx the parse tree
	 */
	void enterFunction_argument(cqlParser.Function_argumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#function_argument}.
	 * @param ctx the parse tree
	 */
	void exitFunction_argument(cqlParser.Function_argumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#literal_list}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_list(cqlParser.Literal_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#literal_list}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_list(cqlParser.Literal_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(cqlParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(cqlParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#state_name}.
	 * @param ctx the parse tree
	 */
	void enterState_name(cqlParser.State_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#state_name}.
	 * @param ctx the parse tree
	 */
	void exitState_name(cqlParser.State_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(cqlParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(cqlParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#compare_dates}.
	 * @param ctx the parse tree
	 */
	void enterCompare_dates(cqlParser.Compare_datesContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#compare_dates}.
	 * @param ctx the parse tree
	 */
	void exitCompare_dates(cqlParser.Compare_datesContext ctx);
	/**
	 * Enter a parse tree produced by {@link cqlParser#dates}.
	 * @param ctx the parse tree
	 */
	void enterDates(cqlParser.DatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link cqlParser#dates}.
	 * @param ctx the parse tree
	 */
	void exitDates(cqlParser.DatesContext ctx);
}