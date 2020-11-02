// Generated from /Users/shamil/Documents/Projects/conductor.dev/Conductor/src/main/antlr4/dev/conductor/cql/cql.g4 by ANTLR 4.8
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
	 * Visit a parse tree produced by {@link cqlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(cqlParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link cqlParser#ordering_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdering_term(cqlParser.Ordering_termContext ctx);
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