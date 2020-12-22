// Generated from D:\soft\free\centra\Centra\tools\bat\..\..\src\main\antlr4\dev\conductor\cql\cql.g4 by ANTLR 4.9
package dev.conductor.centra.domain.search.cql;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link cqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface cqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link cqlParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(cqlParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#cql_stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCql_stmt_list(cqlParser.Cql_stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#cql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCql_stmt(cqlParser.Cql_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndLogicalExpression(cqlParser.AndLogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrLogicalExpression(cqlParser.OrLogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BracedExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracedExpression(cqlParser.BracedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegatedLogicalExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegatedLogicalExpression(cqlParser.NegatedLogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleExpression}
	 * labeled alternative in {@link cqlParser#logical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(cqlParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(cqlParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#right_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRight_value(cqlParser.Right_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#left_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeft_value(cqlParser.Left_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#ordering_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdering_term(cqlParser.Ordering_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#ordering_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdering_list(cqlParser.Ordering_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#ordering_list_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdering_list_item(cqlParser.Ordering_list_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#order_by}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder_by(cqlParser.Order_byContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(cqlParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#literal_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_value(cqlParser.Literal_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(cqlParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list(cqlParser.Argument_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#function_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_argument(cqlParser.Function_argumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#literal_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_list(cqlParser.Literal_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(cqlParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#state_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitState_name(cqlParser.State_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(cqlParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#compare_dates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare_dates(cqlParser.Compare_datesContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#dates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDates(cqlParser.DatesContext ctx);
}