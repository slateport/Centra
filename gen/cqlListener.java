// Generated from /Users/shamil/Documents/Projects/conductor.dev/Conductor/src/main/antlr4/dev/conductor/cql/cql.g4 by ANTLR 4.8
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