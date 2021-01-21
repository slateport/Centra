// Generated from D:\soft\free\centra\Centra\tools\bat\..\..\src\main\antlr4\dev\conductor\cql\cql.g4 by ANTLR 4.9
package dev.conductor.centra.domain.search.cql;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class cqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, DATETIME_LITERAL_QUOTED_TYPE1=7, 
		DATETIME_LITERAL_QUOTED_TYPE2=8, DATETIME_LITERAL=9, NUMBER=10, WHITESPACE=11, 
		SCOL=12, DOT=13, OPEN_PAR=14, CLOSE_PAR=15, COMMA=16, EQ=17, STAR=18, 
		CONTAINS=19, NOT_CONTAINS=20, LT=21, LT_EQ=22, GT=23, GT_EQ=24, NOT_EQ=25, 
		K_AFTER=26, K_AND=27, K_ASC=28, K_BEFORE=29, K_BY=30, K_CHANGED=31, K_DESC=32, 
		K_EMPTY=33, K_IN=34, K_IS=35, K_NOT=36, K_NULL=37, K_ON=38, K_OR=39, K_ORDER=40, 
		K_TO=41, K_WAS=42, F_AFFECTED_VERSION=43, F_APPROVALS=44, F_ASSIGNEE=45, 
		F_ATTACHMENTS=46, F_CATEGORY=47, F_COMMENT=48, F_COMPONENT=49, F_CREATED=50, 
		F_CREATED_DATE=51, F_CREATOR=52, F_CUSTOM_FIELD=53, F_CUSTOMER_REQUEST_TYPE=54, 
		F_DATE=55, F_DESCRIPTION=56, F_DUE=57, F_DURATION=58, F_ENVIRONMENT=59, 
		F_EPIC_LINK=60, F_FILTER=61, F_FIX_VERSION=62, F_ISSUE=63, F_ISSUE_KEY=64, 
		F_ISSUE_TYPE=65, F_KEY=66, F_LABEL=67, F_LABELS=68, F_LAST_VIEWED=69, 
		F_LEVEL=70, F_NUMBER=71, F_ORGANIZATION=72, F_ORIGINAL_ESTIMATE=73, F_PARENT=74, 
		F_PRIORITY=75, F_PROJECT=76, F_PROJECT_KEY=77, F_RANK=78, F_REMAINING_ESTIMATE=79, 
		F_REPORTER=80, F_REQUEST_CHANNEL_TYPE=81, F_REQUEST_LAST_ACTIVITY_TIME=82, 
		F_RESOLUTION=83, F_RESOLUTION_DATE=84, F_RESOLVED=85, F_SLA=86, F_SPRINT=87, 
		F_STATUS=88, F_SUMMARY=89, F_TEXT=90, F_TIME_SPENT=91, F_TYPE=92, F_UPDATED=93, 
		F_USER=94, F_VERSION=95, F_VOTER=96, F_VOTES=97, F_WATCHER=98, F_WATCHERS=99, 
		F_WORK_RATIO=100, IDENTIFIER=101, STRING_LITERAL=102, COMMENT=103, LINE_COMMENT=104, 
		SPACES=105, ErrorCharacter=106;
	public static final int
		RULE_parse = 0, RULE_cql_stmt_list = 1, RULE_cql_stmt = 2, RULE_logical_expression = 3, 
		RULE_expr = 4, RULE_right_value = 5, RULE_left_value = 6, RULE_ordering_term = 7, 
		RULE_ordering_list = 8, RULE_ordering_list_item = 9, RULE_order_by_argument = 10, 
		RULE_order_by = 11, RULE_operator = 12, RULE_is_operator = 13, RULE_literal_value = 14, 
		RULE_function_call = 15, RULE_argument_list = 16, RULE_function_argument = 17, 
		RULE_literal_list = 18, RULE_keyword = 19, RULE_state_name = 20, RULE_field = 21, 
		RULE_dates = 22, RULE_number_and_term = 23, RULE_number = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "cql_stmt_list", "cql_stmt", "logical_expression", "expr", "right_value", 
			"left_value", "ordering_term", "ordering_list", "ordering_list_item", 
			"order_by_argument", "order_by", "operator", "is_operator", "literal_value", 
			"function_call", "argument_list", "function_argument", "literal_list", 
			"keyword", "state_name", "field", "dates", "number_and_term", "number"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'d'", "'w'", "'M'", "'y'", "'h'", "'m'", null, null, null, null, 
			"' '", "';'", "'.'", "'('", "')'", "','", "'='", "'*'", "'~'", "'!~'", 
			"'<'", "'<='", "'>'", "'>='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "DATETIME_LITERAL_QUOTED_TYPE1", 
			"DATETIME_LITERAL_QUOTED_TYPE2", "DATETIME_LITERAL", "NUMBER", "WHITESPACE", 
			"SCOL", "DOT", "OPEN_PAR", "CLOSE_PAR", "COMMA", "EQ", "STAR", "CONTAINS", 
			"NOT_CONTAINS", "LT", "LT_EQ", "GT", "GT_EQ", "NOT_EQ", "K_AFTER", "K_AND", 
			"K_ASC", "K_BEFORE", "K_BY", "K_CHANGED", "K_DESC", "K_EMPTY", "K_IN", 
			"K_IS", "K_NOT", "K_NULL", "K_ON", "K_OR", "K_ORDER", "K_TO", "K_WAS", 
			"F_AFFECTED_VERSION", "F_APPROVALS", "F_ASSIGNEE", "F_ATTACHMENTS", "F_CATEGORY", 
			"F_COMMENT", "F_COMPONENT", "F_CREATED", "F_CREATED_DATE", "F_CREATOR", 
			"F_CUSTOM_FIELD", "F_CUSTOMER_REQUEST_TYPE", "F_DATE", "F_DESCRIPTION", 
			"F_DUE", "F_DURATION", "F_ENVIRONMENT", "F_EPIC_LINK", "F_FILTER", "F_FIX_VERSION", 
			"F_ISSUE", "F_ISSUE_KEY", "F_ISSUE_TYPE", "F_KEY", "F_LABEL", "F_LABELS", 
			"F_LAST_VIEWED", "F_LEVEL", "F_NUMBER", "F_ORGANIZATION", "F_ORIGINAL_ESTIMATE", 
			"F_PARENT", "F_PRIORITY", "F_PROJECT", "F_PROJECT_KEY", "F_RANK", "F_REMAINING_ESTIMATE", 
			"F_REPORTER", "F_REQUEST_CHANNEL_TYPE", "F_REQUEST_LAST_ACTIVITY_TIME", 
			"F_RESOLUTION", "F_RESOLUTION_DATE", "F_RESOLVED", "F_SLA", "F_SPRINT", 
			"F_STATUS", "F_SUMMARY", "F_TEXT", "F_TIME_SPENT", "F_TYPE", "F_UPDATED", 
			"F_USER", "F_VERSION", "F_VOTER", "F_VOTES", "F_WATCHER", "F_WATCHERS", 
			"F_WORK_RATIO", "IDENTIFIER", "STRING_LITERAL", "COMMENT", "LINE_COMMENT", 
			"SPACES", "ErrorCharacter"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "cql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public cqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(cqlParser.EOF, 0); }
		public List<Cql_stmt_listContext> cql_stmt_list() {
			return getRuleContexts(Cql_stmt_listContext.class);
		}
		public Cql_stmt_listContext cql_stmt_list(int i) {
			return getRuleContext(Cql_stmt_listContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SCOL) | (1L << OPEN_PAR) | (1L << K_NOT) | (1L << F_AFFECTED_VERSION) | (1L << F_APPROVALS) | (1L << F_ASSIGNEE) | (1L << F_ATTACHMENTS) | (1L << F_CATEGORY) | (1L << F_COMMENT) | (1L << F_COMPONENT) | (1L << F_CREATED) | (1L << F_CREATED_DATE) | (1L << F_CREATOR) | (1L << F_CUSTOM_FIELD) | (1L << F_CUSTOMER_REQUEST_TYPE) | (1L << F_DATE) | (1L << F_DESCRIPTION) | (1L << F_DUE) | (1L << F_DURATION) | (1L << F_ENVIRONMENT) | (1L << F_EPIC_LINK) | (1L << F_FILTER) | (1L << F_FIX_VERSION) | (1L << F_ISSUE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (F_ISSUE_KEY - 64)) | (1L << (F_ISSUE_TYPE - 64)) | (1L << (F_KEY - 64)) | (1L << (F_LABEL - 64)) | (1L << (F_LABELS - 64)) | (1L << (F_LAST_VIEWED - 64)) | (1L << (F_LEVEL - 64)) | (1L << (F_NUMBER - 64)) | (1L << (F_ORGANIZATION - 64)) | (1L << (F_ORIGINAL_ESTIMATE - 64)) | (1L << (F_PARENT - 64)) | (1L << (F_PRIORITY - 64)) | (1L << (F_PROJECT - 64)) | (1L << (F_PROJECT_KEY - 64)) | (1L << (F_RANK - 64)) | (1L << (F_REMAINING_ESTIMATE - 64)) | (1L << (F_REPORTER - 64)) | (1L << (F_REQUEST_CHANNEL_TYPE - 64)) | (1L << (F_REQUEST_LAST_ACTIVITY_TIME - 64)) | (1L << (F_RESOLUTION - 64)) | (1L << (F_RESOLUTION_DATE - 64)) | (1L << (F_RESOLVED - 64)) | (1L << (F_SLA - 64)) | (1L << (F_SPRINT - 64)) | (1L << (F_STATUS - 64)) | (1L << (F_SUMMARY - 64)) | (1L << (F_TEXT - 64)) | (1L << (F_TIME_SPENT - 64)) | (1L << (F_TYPE - 64)) | (1L << (F_UPDATED - 64)) | (1L << (F_USER - 64)) | (1L << (F_VERSION - 64)) | (1L << (F_VOTER - 64)) | (1L << (F_VOTES - 64)) | (1L << (F_WATCHER - 64)) | (1L << (F_WATCHERS - 64)) | (1L << (F_WORK_RATIO - 64)))) != 0)) {
				{
				{
				setState(50);
				cql_stmt_list();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cql_stmt_listContext extends ParserRuleContext {
		public List<Cql_stmtContext> cql_stmt() {
			return getRuleContexts(Cql_stmtContext.class);
		}
		public Cql_stmtContext cql_stmt(int i) {
			return getRuleContext(Cql_stmtContext.class,i);
		}
		public List<TerminalNode> SCOL() { return getTokens(cqlParser.SCOL); }
		public TerminalNode SCOL(int i) {
			return getToken(cqlParser.SCOL, i);
		}
		public Cql_stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cql_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterCql_stmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitCql_stmt_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitCql_stmt_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cql_stmt_listContext cql_stmt_list() throws RecognitionException {
		Cql_stmt_listContext _localctx = new Cql_stmt_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCOL) {
				{
				setState(58);
				match(SCOL);
				}
			}

			setState(61);
			cql_stmt();
			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(63); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(62);
						match(SCOL);
						}
						}
						setState(65); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SCOL );
					setState(67);
					cql_stmt();
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(73);
				match(SCOL);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cql_stmtContext extends ParserRuleContext {
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public Ordering_termContext ordering_term() {
			return getRuleContext(Ordering_termContext.class,0);
		}
		public Cql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterCql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitCql_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitCql_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cql_stmtContext cql_stmt() throws RecognitionException {
		Cql_stmtContext _localctx = new Cql_stmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_cql_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			logical_expression(0);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(77);
				ordering_term();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logical_expressionContext extends ParserRuleContext {
		public Logical_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_expression; }
	 
		public Logical_expressionContext() { }
		public void copyFrom(Logical_expressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AndLogicalExpressionContext extends Logical_expressionContext {
		public List<Logical_expressionContext> logical_expression() {
			return getRuleContexts(Logical_expressionContext.class);
		}
		public Logical_expressionContext logical_expression(int i) {
			return getRuleContext(Logical_expressionContext.class,i);
		}
		public TerminalNode K_AND() { return getToken(cqlParser.K_AND, 0); }
		public AndLogicalExpressionContext(Logical_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterAndLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitAndLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitAndLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrLogicalExpressionContext extends Logical_expressionContext {
		public List<Logical_expressionContext> logical_expression() {
			return getRuleContexts(Logical_expressionContext.class);
		}
		public Logical_expressionContext logical_expression(int i) {
			return getRuleContext(Logical_expressionContext.class,i);
		}
		public TerminalNode K_OR() { return getToken(cqlParser.K_OR, 0); }
		public OrLogicalExpressionContext(Logical_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOrLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOrLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOrLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracedExpressionContext extends Logical_expressionContext {
		public TerminalNode OPEN_PAR() { return getToken(cqlParser.OPEN_PAR, 0); }
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public TerminalNode CLOSE_PAR() { return getToken(cqlParser.CLOSE_PAR, 0); }
		public BracedExpressionContext(Logical_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterBracedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitBracedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitBracedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegatedLogicalExpressionContext extends Logical_expressionContext {
		public TerminalNode K_NOT() { return getToken(cqlParser.K_NOT, 0); }
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public NegatedLogicalExpressionContext(Logical_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterNegatedLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitNegatedLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitNegatedLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleExpressionContext extends Logical_expressionContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SimpleExpressionContext(Logical_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterSimpleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitSimpleExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitSimpleExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_expressionContext logical_expression() throws RecognitionException {
		return logical_expression(0);
	}

	private Logical_expressionContext logical_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Logical_expressionContext _localctx = new Logical_expressionContext(_ctx, _parentState);
		Logical_expressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_logical_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAR:
				{
				_localctx = new BracedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(81);
				match(OPEN_PAR);
				setState(82);
				logical_expression(0);
				setState(83);
				match(CLOSE_PAR);
				}
				break;
			case F_AFFECTED_VERSION:
			case F_APPROVALS:
			case F_ASSIGNEE:
			case F_ATTACHMENTS:
			case F_CATEGORY:
			case F_COMMENT:
			case F_COMPONENT:
			case F_CREATED:
			case F_CREATED_DATE:
			case F_CREATOR:
			case F_CUSTOM_FIELD:
			case F_CUSTOMER_REQUEST_TYPE:
			case F_DATE:
			case F_DESCRIPTION:
			case F_DUE:
			case F_DURATION:
			case F_ENVIRONMENT:
			case F_EPIC_LINK:
			case F_FILTER:
			case F_FIX_VERSION:
			case F_ISSUE:
			case F_ISSUE_KEY:
			case F_ISSUE_TYPE:
			case F_KEY:
			case F_LABEL:
			case F_LABELS:
			case F_LAST_VIEWED:
			case F_LEVEL:
			case F_NUMBER:
			case F_ORGANIZATION:
			case F_ORIGINAL_ESTIMATE:
			case F_PARENT:
			case F_PRIORITY:
			case F_PROJECT:
			case F_PROJECT_KEY:
			case F_RANK:
			case F_REMAINING_ESTIMATE:
			case F_REPORTER:
			case F_REQUEST_CHANNEL_TYPE:
			case F_REQUEST_LAST_ACTIVITY_TIME:
			case F_RESOLUTION:
			case F_RESOLUTION_DATE:
			case F_RESOLVED:
			case F_SLA:
			case F_SPRINT:
			case F_STATUS:
			case F_SUMMARY:
			case F_TEXT:
			case F_TIME_SPENT:
			case F_TYPE:
			case F_UPDATED:
			case F_USER:
			case F_VERSION:
			case F_VOTER:
			case F_VOTES:
			case F_WATCHER:
			case F_WATCHERS:
			case F_WORK_RATIO:
				{
				_localctx = new SimpleExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				expr();
				}
				break;
			case K_NOT:
				{
				_localctx = new NegatedLogicalExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				match(K_NOT);
				setState(87);
				logical_expression(3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(98);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(96);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new AndLogicalExpressionContext(new Logical_expressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expression);
						setState(90);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(91);
						match(K_AND);
						setState(92);
						logical_expression(3);
						}
						break;
					case 2:
						{
						_localctx = new OrLogicalExpressionContext(new Logical_expressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expression);
						setState(93);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(94);
						match(K_OR);
						setState(95);
						logical_expression(2);
						}
						break;
					}
					} 
				}
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Token operand;
		public Left_valueContext left_value() {
			return getRuleContext(Left_valueContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public Right_valueContext right_value() {
			return getRuleContext(Right_valueContext.class,0);
		}
		public Is_operatorContext is_operator() {
			return getRuleContext(Is_operatorContext.class,0);
		}
		public TerminalNode K_EMPTY() { return getToken(cqlParser.K_EMPTY, 0); }
		public TerminalNode K_NULL() { return getToken(cqlParser.K_NULL, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expr);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				left_value();
				setState(102);
				operator();
				setState(103);
				right_value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				left_value();
				setState(106);
				is_operator();
				setState(107);
				((ExprContext)_localctx).operand = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==K_EMPTY || _la==K_NULL) ) {
					((ExprContext)_localctx).operand = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Right_valueContext extends ParserRuleContext {
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Literal_listContext literal_list() {
			return getRuleContext(Literal_listContext.class,0);
		}
		public DatesContext dates() {
			return getRuleContext(DatesContext.class,0);
		}
		public Right_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterRight_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitRight_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitRight_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Right_valueContext right_value() throws RecognitionException {
		Right_valueContext _localctx = new Right_valueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_right_value);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				literal_value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				function_call();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				literal_list();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				dates();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Left_valueContext extends ParserRuleContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Left_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterLeft_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitLeft_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitLeft_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Left_valueContext left_value() throws RecognitionException {
		Left_valueContext _localctx = new Left_valueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_left_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			field();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ordering_termContext extends ParserRuleContext {
		public Order_byContext order_by() {
			return getRuleContext(Order_byContext.class,0);
		}
		public Ordering_listContext ordering_list() {
			return getRuleContext(Ordering_listContext.class,0);
		}
		public Ordering_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOrdering_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOrdering_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOrdering_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ordering_termContext ordering_term() throws RecognitionException {
		Ordering_termContext _localctx = new Ordering_termContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ordering_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			order_by();
			setState(120);
			ordering_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ordering_listContext extends ParserRuleContext {
		public List<Ordering_list_itemContext> ordering_list_item() {
			return getRuleContexts(Ordering_list_itemContext.class);
		}
		public Ordering_list_itemContext ordering_list_item(int i) {
			return getRuleContext(Ordering_list_itemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(cqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(cqlParser.COMMA, i);
		}
		public Ordering_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOrdering_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOrdering_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOrdering_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ordering_listContext ordering_list() throws RecognitionException {
		Ordering_listContext _localctx = new Ordering_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ordering_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			ordering_list_item();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(123);
				match(COMMA);
				setState(124);
				ordering_list_item();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ordering_list_itemContext extends ParserRuleContext {
		public Token order;
		public Order_by_argumentContext order_by_argument() {
			return getRuleContext(Order_by_argumentContext.class,0);
		}
		public TerminalNode K_ASC() { return getToken(cqlParser.K_ASC, 0); }
		public TerminalNode K_DESC() { return getToken(cqlParser.K_DESC, 0); }
		public Ordering_list_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_list_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOrdering_list_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOrdering_list_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOrdering_list_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ordering_list_itemContext ordering_list_item() throws RecognitionException {
		Ordering_list_itemContext _localctx = new Ordering_list_itemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ordering_list_item);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			order_by_argument();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(131);
				((Ordering_list_itemContext)_localctx).order = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==K_ASC || _la==K_DESC) ) {
					((Ordering_list_itemContext)_localctx).order = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Order_by_argumentContext extends ParserRuleContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Order_by_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order_by_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOrder_by_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOrder_by_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOrder_by_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Order_by_argumentContext order_by_argument() throws RecognitionException {
		Order_by_argumentContext _localctx = new Order_by_argumentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_order_by_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			field();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Order_byContext extends ParserRuleContext {
		public TerminalNode K_ORDER() { return getToken(cqlParser.K_ORDER, 0); }
		public TerminalNode K_BY() { return getToken(cqlParser.K_BY, 0); }
		public Order_byContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order_by; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOrder_by(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOrder_by(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOrder_by(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Order_byContext order_by() throws RecognitionException {
		Order_byContext _localctx = new Order_byContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_order_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(K_ORDER);
			setState(137);
			match(K_BY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(cqlParser.EQ, 0); }
		public TerminalNode NOT_EQ() { return getToken(cqlParser.NOT_EQ, 0); }
		public TerminalNode CONTAINS() { return getToken(cqlParser.CONTAINS, 0); }
		public TerminalNode NOT_CONTAINS() { return getToken(cqlParser.NOT_CONTAINS, 0); }
		public TerminalNode LT_EQ() { return getToken(cqlParser.LT_EQ, 0); }
		public TerminalNode LT() { return getToken(cqlParser.LT, 0); }
		public TerminalNode GT() { return getToken(cqlParser.GT, 0); }
		public TerminalNode GT_EQ() { return getToken(cqlParser.GT_EQ, 0); }
		public TerminalNode K_IN() { return getToken(cqlParser.K_IN, 0); }
		public TerminalNode K_NOT() { return getToken(cqlParser.K_NOT, 0); }
		public TerminalNode K_WAS() { return getToken(cqlParser.K_WAS, 0); }
		public TerminalNode K_CHANGED() { return getToken(cqlParser.K_CHANGED, 0); }
		public TerminalNode K_TO() { return getToken(cqlParser.K_TO, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operator);
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				match(EQ);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				match(NOT_EQ);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				match(CONTAINS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				match(NOT_CONTAINS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				match(LT_EQ);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				match(LT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(145);
				match(GT);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(146);
				match(GT_EQ);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(147);
				match(K_IN);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(148);
				match(K_NOT);
				setState(149);
				match(K_IN);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(150);
				match(K_WAS);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(151);
				match(K_WAS);
				setState(152);
				match(K_NOT);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(153);
				match(K_CHANGED);
				setState(154);
				match(K_TO);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_operatorContext extends ParserRuleContext {
		public TerminalNode K_IS() { return getToken(cqlParser.K_IS, 0); }
		public TerminalNode K_NOT() { return getToken(cqlParser.K_NOT, 0); }
		public Is_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_is_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterIs_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitIs_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitIs_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Is_operatorContext is_operator() throws RecognitionException {
		Is_operatorContext _localctx = new Is_operatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_is_operator);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(K_IS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(K_IS);
				setState(159);
				match(K_NOT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(cqlParser.STRING_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(cqlParser.IDENTIFIER, 0); }
		public State_nameContext state_name() {
			return getRuleContext(State_nameContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public DatesContext dates() {
			return getRuleContext(DatesContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitLiteral_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitLiteral_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literal_value);
		try {
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(STRING_LITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				state_name();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				field();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				dates();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(167);
				number();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(cqlParser.IDENTIFIER, 0); }
		public TerminalNode OPEN_PAR() { return getToken(cqlParser.OPEN_PAR, 0); }
		public TerminalNode CLOSE_PAR() { return getToken(cqlParser.CLOSE_PAR, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(IDENTIFIER);
			setState(171);
			match(OPEN_PAR);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DATETIME_LITERAL_QUOTED_TYPE1) | (1L << DATETIME_LITERAL_QUOTED_TYPE2) | (1L << DATETIME_LITERAL) | (1L << NUMBER) | (1L << K_EMPTY) | (1L << F_AFFECTED_VERSION) | (1L << F_APPROVALS) | (1L << F_ASSIGNEE) | (1L << F_ATTACHMENTS) | (1L << F_CATEGORY) | (1L << F_COMMENT) | (1L << F_COMPONENT) | (1L << F_CREATED) | (1L << F_CREATED_DATE) | (1L << F_CREATOR) | (1L << F_CUSTOM_FIELD) | (1L << F_CUSTOMER_REQUEST_TYPE) | (1L << F_DATE) | (1L << F_DESCRIPTION) | (1L << F_DUE) | (1L << F_DURATION) | (1L << F_ENVIRONMENT) | (1L << F_EPIC_LINK) | (1L << F_FILTER) | (1L << F_FIX_VERSION) | (1L << F_ISSUE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (F_ISSUE_KEY - 64)) | (1L << (F_ISSUE_TYPE - 64)) | (1L << (F_KEY - 64)) | (1L << (F_LABEL - 64)) | (1L << (F_LABELS - 64)) | (1L << (F_LAST_VIEWED - 64)) | (1L << (F_LEVEL - 64)) | (1L << (F_NUMBER - 64)) | (1L << (F_ORGANIZATION - 64)) | (1L << (F_ORIGINAL_ESTIMATE - 64)) | (1L << (F_PARENT - 64)) | (1L << (F_PRIORITY - 64)) | (1L << (F_PROJECT - 64)) | (1L << (F_PROJECT_KEY - 64)) | (1L << (F_RANK - 64)) | (1L << (F_REMAINING_ESTIMATE - 64)) | (1L << (F_REPORTER - 64)) | (1L << (F_REQUEST_CHANNEL_TYPE - 64)) | (1L << (F_REQUEST_LAST_ACTIVITY_TIME - 64)) | (1L << (F_RESOLUTION - 64)) | (1L << (F_RESOLUTION_DATE - 64)) | (1L << (F_RESOLVED - 64)) | (1L << (F_SLA - 64)) | (1L << (F_SPRINT - 64)) | (1L << (F_STATUS - 64)) | (1L << (F_SUMMARY - 64)) | (1L << (F_TEXT - 64)) | (1L << (F_TIME_SPENT - 64)) | (1L << (F_TYPE - 64)) | (1L << (F_UPDATED - 64)) | (1L << (F_USER - 64)) | (1L << (F_VERSION - 64)) | (1L << (F_VOTER - 64)) | (1L << (F_VOTES - 64)) | (1L << (F_WATCHER - 64)) | (1L << (F_WATCHERS - 64)) | (1L << (F_WORK_RATIO - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0)) {
				{
				setState(172);
				argument_list();
				}
			}

			setState(175);
			match(CLOSE_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Argument_listContext extends ParserRuleContext {
		public List<Function_argumentContext> function_argument() {
			return getRuleContexts(Function_argumentContext.class);
		}
		public Function_argumentContext function_argument(int i) {
			return getRuleContext(Function_argumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(cqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(cqlParser.COMMA, i);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterArgument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitArgument_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitArgument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			function_argument();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(178);
				match(COMMA);
				setState(179);
				function_argument();
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_argumentContext extends ParserRuleContext {
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Function_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterFunction_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitFunction_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitFunction_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_argumentContext function_argument() throws RecognitionException {
		Function_argumentContext _localctx = new Function_argumentContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_function_argument);
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				literal_value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				function_call();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_listContext extends ParserRuleContext {
		public TerminalNode OPEN_PAR() { return getToken(cqlParser.OPEN_PAR, 0); }
		public List<Literal_valueContext> literal_value() {
			return getRuleContexts(Literal_valueContext.class);
		}
		public Literal_valueContext literal_value(int i) {
			return getRuleContext(Literal_valueContext.class,i);
		}
		public TerminalNode CLOSE_PAR() { return getToken(cqlParser.CLOSE_PAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(cqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(cqlParser.COMMA, i);
		}
		public Literal_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterLiteral_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitLiteral_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitLiteral_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_listContext literal_list() throws RecognitionException {
		Literal_listContext _localctx = new Literal_listContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_literal_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(OPEN_PAR);
			setState(190);
			literal_value();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(191);
				match(COMMA);
				setState(192);
				literal_value();
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(198);
			match(CLOSE_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode K_AFTER() { return getToken(cqlParser.K_AFTER, 0); }
		public TerminalNode K_AND() { return getToken(cqlParser.K_AND, 0); }
		public TerminalNode K_ASC() { return getToken(cqlParser.K_ASC, 0); }
		public TerminalNode K_BEFORE() { return getToken(cqlParser.K_BEFORE, 0); }
		public TerminalNode K_BY() { return getToken(cqlParser.K_BY, 0); }
		public TerminalNode K_CHANGED() { return getToken(cqlParser.K_CHANGED, 0); }
		public TerminalNode K_DESC() { return getToken(cqlParser.K_DESC, 0); }
		public TerminalNode K_IN() { return getToken(cqlParser.K_IN, 0); }
		public TerminalNode K_IS() { return getToken(cqlParser.K_IS, 0); }
		public TerminalNode K_NOT() { return getToken(cqlParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(cqlParser.K_NULL, 0); }
		public TerminalNode K_ON() { return getToken(cqlParser.K_ON, 0); }
		public TerminalNode K_OR() { return getToken(cqlParser.K_OR, 0); }
		public TerminalNode K_ORDER() { return getToken(cqlParser.K_ORDER, 0); }
		public TerminalNode K_TO() { return getToken(cqlParser.K_TO, 0); }
		public TerminalNode K_WAS() { return getToken(cqlParser.K_WAS, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_AFTER) | (1L << K_AND) | (1L << K_ASC) | (1L << K_BEFORE) | (1L << K_BY) | (1L << K_CHANGED) | (1L << K_DESC) | (1L << K_IN) | (1L << K_IS) | (1L << K_NOT) | (1L << K_NULL) | (1L << K_ON) | (1L << K_OR) | (1L << K_ORDER) | (1L << K_TO) | (1L << K_WAS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class State_nameContext extends ParserRuleContext {
		public TerminalNode K_EMPTY() { return getToken(cqlParser.K_EMPTY, 0); }
		public State_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterState_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitState_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitState_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final State_nameContext state_name() throws RecognitionException {
		State_nameContext _localctx = new State_nameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_state_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(K_EMPTY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public TerminalNode F_AFFECTED_VERSION() { return getToken(cqlParser.F_AFFECTED_VERSION, 0); }
		public TerminalNode F_APPROVALS() { return getToken(cqlParser.F_APPROVALS, 0); }
		public TerminalNode F_ASSIGNEE() { return getToken(cqlParser.F_ASSIGNEE, 0); }
		public TerminalNode F_ATTACHMENTS() { return getToken(cqlParser.F_ATTACHMENTS, 0); }
		public TerminalNode F_CATEGORY() { return getToken(cqlParser.F_CATEGORY, 0); }
		public TerminalNode F_COMMENT() { return getToken(cqlParser.F_COMMENT, 0); }
		public TerminalNode F_COMPONENT() { return getToken(cqlParser.F_COMPONENT, 0); }
		public TerminalNode F_CREATED() { return getToken(cqlParser.F_CREATED, 0); }
		public TerminalNode F_CREATED_DATE() { return getToken(cqlParser.F_CREATED_DATE, 0); }
		public TerminalNode F_CREATOR() { return getToken(cqlParser.F_CREATOR, 0); }
		public TerminalNode F_CUSTOM_FIELD() { return getToken(cqlParser.F_CUSTOM_FIELD, 0); }
		public TerminalNode F_CUSTOMER_REQUEST_TYPE() { return getToken(cqlParser.F_CUSTOMER_REQUEST_TYPE, 0); }
		public TerminalNode F_DATE() { return getToken(cqlParser.F_DATE, 0); }
		public TerminalNode F_DESCRIPTION() { return getToken(cqlParser.F_DESCRIPTION, 0); }
		public TerminalNode F_DUE() { return getToken(cqlParser.F_DUE, 0); }
		public TerminalNode F_DURATION() { return getToken(cqlParser.F_DURATION, 0); }
		public TerminalNode F_ENVIRONMENT() { return getToken(cqlParser.F_ENVIRONMENT, 0); }
		public TerminalNode F_EPIC_LINK() { return getToken(cqlParser.F_EPIC_LINK, 0); }
		public TerminalNode F_FILTER() { return getToken(cqlParser.F_FILTER, 0); }
		public TerminalNode F_FIX_VERSION() { return getToken(cqlParser.F_FIX_VERSION, 0); }
		public TerminalNode F_ISSUE() { return getToken(cqlParser.F_ISSUE, 0); }
		public TerminalNode F_ISSUE_KEY() { return getToken(cqlParser.F_ISSUE_KEY, 0); }
		public TerminalNode F_ISSUE_TYPE() { return getToken(cqlParser.F_ISSUE_TYPE, 0); }
		public TerminalNode F_KEY() { return getToken(cqlParser.F_KEY, 0); }
		public TerminalNode F_LABEL() { return getToken(cqlParser.F_LABEL, 0); }
		public TerminalNode F_LABELS() { return getToken(cqlParser.F_LABELS, 0); }
		public TerminalNode F_LAST_VIEWED() { return getToken(cqlParser.F_LAST_VIEWED, 0); }
		public TerminalNode F_LEVEL() { return getToken(cqlParser.F_LEVEL, 0); }
		public TerminalNode F_NUMBER() { return getToken(cqlParser.F_NUMBER, 0); }
		public TerminalNode F_ORGANIZATION() { return getToken(cqlParser.F_ORGANIZATION, 0); }
		public TerminalNode F_ORIGINAL_ESTIMATE() { return getToken(cqlParser.F_ORIGINAL_ESTIMATE, 0); }
		public TerminalNode F_PARENT() { return getToken(cqlParser.F_PARENT, 0); }
		public TerminalNode F_PRIORITY() { return getToken(cqlParser.F_PRIORITY, 0); }
		public TerminalNode F_PROJECT() { return getToken(cqlParser.F_PROJECT, 0); }
		public TerminalNode F_PROJECT_KEY() { return getToken(cqlParser.F_PROJECT_KEY, 0); }
		public TerminalNode F_RANK() { return getToken(cqlParser.F_RANK, 0); }
		public TerminalNode F_REMAINING_ESTIMATE() { return getToken(cqlParser.F_REMAINING_ESTIMATE, 0); }
		public TerminalNode F_REPORTER() { return getToken(cqlParser.F_REPORTER, 0); }
		public TerminalNode F_REQUEST_CHANNEL_TYPE() { return getToken(cqlParser.F_REQUEST_CHANNEL_TYPE, 0); }
		public TerminalNode F_REQUEST_LAST_ACTIVITY_TIME() { return getToken(cqlParser.F_REQUEST_LAST_ACTIVITY_TIME, 0); }
		public TerminalNode F_RESOLUTION() { return getToken(cqlParser.F_RESOLUTION, 0); }
		public TerminalNode F_RESOLUTION_DATE() { return getToken(cqlParser.F_RESOLUTION_DATE, 0); }
		public TerminalNode F_RESOLVED() { return getToken(cqlParser.F_RESOLVED, 0); }
		public TerminalNode F_SLA() { return getToken(cqlParser.F_SLA, 0); }
		public TerminalNode F_SPRINT() { return getToken(cqlParser.F_SPRINT, 0); }
		public TerminalNode F_STATUS() { return getToken(cqlParser.F_STATUS, 0); }
		public TerminalNode F_SUMMARY() { return getToken(cqlParser.F_SUMMARY, 0); }
		public TerminalNode F_TEXT() { return getToken(cqlParser.F_TEXT, 0); }
		public TerminalNode F_TIME_SPENT() { return getToken(cqlParser.F_TIME_SPENT, 0); }
		public TerminalNode F_TYPE() { return getToken(cqlParser.F_TYPE, 0); }
		public TerminalNode F_UPDATED() { return getToken(cqlParser.F_UPDATED, 0); }
		public TerminalNode F_USER() { return getToken(cqlParser.F_USER, 0); }
		public TerminalNode F_VERSION() { return getToken(cqlParser.F_VERSION, 0); }
		public TerminalNode F_VOTER() { return getToken(cqlParser.F_VOTER, 0); }
		public TerminalNode F_VOTES() { return getToken(cqlParser.F_VOTES, 0); }
		public TerminalNode F_WATCHER() { return getToken(cqlParser.F_WATCHER, 0); }
		public TerminalNode F_WATCHERS() { return getToken(cqlParser.F_WATCHERS, 0); }
		public TerminalNode F_WORK_RATIO() { return getToken(cqlParser.F_WORK_RATIO, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !(((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (F_AFFECTED_VERSION - 43)) | (1L << (F_APPROVALS - 43)) | (1L << (F_ASSIGNEE - 43)) | (1L << (F_ATTACHMENTS - 43)) | (1L << (F_CATEGORY - 43)) | (1L << (F_COMMENT - 43)) | (1L << (F_COMPONENT - 43)) | (1L << (F_CREATED - 43)) | (1L << (F_CREATED_DATE - 43)) | (1L << (F_CREATOR - 43)) | (1L << (F_CUSTOM_FIELD - 43)) | (1L << (F_CUSTOMER_REQUEST_TYPE - 43)) | (1L << (F_DATE - 43)) | (1L << (F_DESCRIPTION - 43)) | (1L << (F_DUE - 43)) | (1L << (F_DURATION - 43)) | (1L << (F_ENVIRONMENT - 43)) | (1L << (F_EPIC_LINK - 43)) | (1L << (F_FILTER - 43)) | (1L << (F_FIX_VERSION - 43)) | (1L << (F_ISSUE - 43)) | (1L << (F_ISSUE_KEY - 43)) | (1L << (F_ISSUE_TYPE - 43)) | (1L << (F_KEY - 43)) | (1L << (F_LABEL - 43)) | (1L << (F_LABELS - 43)) | (1L << (F_LAST_VIEWED - 43)) | (1L << (F_LEVEL - 43)) | (1L << (F_NUMBER - 43)) | (1L << (F_ORGANIZATION - 43)) | (1L << (F_ORIGINAL_ESTIMATE - 43)) | (1L << (F_PARENT - 43)) | (1L << (F_PRIORITY - 43)) | (1L << (F_PROJECT - 43)) | (1L << (F_PROJECT_KEY - 43)) | (1L << (F_RANK - 43)) | (1L << (F_REMAINING_ESTIMATE - 43)) | (1L << (F_REPORTER - 43)) | (1L << (F_REQUEST_CHANNEL_TYPE - 43)) | (1L << (F_REQUEST_LAST_ACTIVITY_TIME - 43)) | (1L << (F_RESOLUTION - 43)) | (1L << (F_RESOLUTION_DATE - 43)) | (1L << (F_RESOLVED - 43)) | (1L << (F_SLA - 43)) | (1L << (F_SPRINT - 43)) | (1L << (F_STATUS - 43)) | (1L << (F_SUMMARY - 43)) | (1L << (F_TEXT - 43)) | (1L << (F_TIME_SPENT - 43)) | (1L << (F_TYPE - 43)) | (1L << (F_UPDATED - 43)) | (1L << (F_USER - 43)) | (1L << (F_VERSION - 43)) | (1L << (F_VOTER - 43)) | (1L << (F_VOTES - 43)) | (1L << (F_WATCHER - 43)) | (1L << (F_WATCHERS - 43)) | (1L << (F_WORK_RATIO - 43)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DatesContext extends ParserRuleContext {
		public DatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dates; }
	 
		public DatesContext() { }
		public void copyFrom(DatesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DateType4Context extends DatesContext {
		public TerminalNode DATETIME_LITERAL_QUOTED_TYPE2() { return getToken(cqlParser.DATETIME_LITERAL_QUOTED_TYPE2, 0); }
		public DateType4Context(DatesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterDateType4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitDateType4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitDateType4(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateType1Context extends DatesContext {
		public List<Number_and_termContext> number_and_term() {
			return getRuleContexts(Number_and_termContext.class);
		}
		public Number_and_termContext number_and_term(int i) {
			return getRuleContext(Number_and_termContext.class,i);
		}
		public DateType1Context(DatesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterDateType1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitDateType1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitDateType1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateType2Context extends DatesContext {
		public TerminalNode DATETIME_LITERAL() { return getToken(cqlParser.DATETIME_LITERAL, 0); }
		public DateType2Context(DatesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterDateType2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitDateType2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitDateType2(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateType3Context extends DatesContext {
		public TerminalNode DATETIME_LITERAL_QUOTED_TYPE1() { return getToken(cqlParser.DATETIME_LITERAL_QUOTED_TYPE1, 0); }
		public DateType3Context(DatesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterDateType3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitDateType3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitDateType3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatesContext dates() throws RecognitionException {
		DatesContext _localctx = new DatesContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dates);
		try {
			int _alt;
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new DateType1Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(207); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(206);
						number_and_term();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(209); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case DATETIME_LITERAL:
				_localctx = new DateType2Context(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
				match(DATETIME_LITERAL);
				}
				break;
			case DATETIME_LITERAL_QUOTED_TYPE1:
				_localctx = new DateType3Context(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				match(DATETIME_LITERAL_QUOTED_TYPE1);
				}
				break;
			case DATETIME_LITERAL_QUOTED_TYPE2:
				_localctx = new DateType4Context(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(213);
				match(DATETIME_LITERAL_QUOTED_TYPE2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Number_and_termContext extends ParserRuleContext {
		public Token unit;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Number_and_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number_and_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterNumber_and_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitNumber_and_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitNumber_and_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Number_and_termContext number_and_term() throws RecognitionException {
		Number_and_termContext _localctx = new Number_and_termContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_number_and_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			number();
			setState(217);
			((Number_and_termContext)_localctx).unit = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
				((Number_and_termContext)_localctx).unit = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(cqlParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cqlListener ) ((cqlListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cqlVisitor ) return ((cqlVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return logical_expression_sempred((Logical_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean logical_expression_sempred(Logical_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3l\u00e0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\7\2\66\n\2\f\2\16\29\13\2\3\2\3\2\3\3\5\3>\n\3\3\3\3\3"+
		"\6\3B\n\3\r\3\16\3C\3\3\7\3G\n\3\f\3\16\3J\13\3\3\3\5\3M\n\3\3\4\3\4\5"+
		"\4Q\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5[\n\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\7\5c\n\5\f\5\16\5f\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6p\n\6\3"+
		"\7\3\7\3\7\3\7\5\7v\n\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\7\n\u0080\n\n"+
		"\f\n\16\n\u0083\13\n\3\13\3\13\5\13\u0087\n\13\3\f\3\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u009e\n\16\3\17\3\17\3\17\5\17\u00a3\n\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\5\20\u00ab\n\20\3\21\3\21\3\21\5\21\u00b0\n\21\3\21\3\21"+
		"\3\22\3\22\3\22\7\22\u00b7\n\22\f\22\16\22\u00ba\13\22\3\23\3\23\5\23"+
		"\u00be\n\23\3\24\3\24\3\24\3\24\7\24\u00c4\n\24\f\24\16\24\u00c7\13\24"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\6\30\u00d2\n\30\r\30\16"+
		"\30\u00d3\3\30\3\30\3\30\5\30\u00d9\n\30\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\2\3\b\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\7\4"+
		"\2##\'\'\4\2\36\36\"\"\4\2\34\"$,\3\2-f\3\2\3\b\2\u00f0\2\67\3\2\2\2\4"+
		"=\3\2\2\2\6N\3\2\2\2\bZ\3\2\2\2\no\3\2\2\2\fu\3\2\2\2\16w\3\2\2\2\20y"+
		"\3\2\2\2\22|\3\2\2\2\24\u0084\3\2\2\2\26\u0088\3\2\2\2\30\u008a\3\2\2"+
		"\2\32\u009d\3\2\2\2\34\u00a2\3\2\2\2\36\u00aa\3\2\2\2 \u00ac\3\2\2\2\""+
		"\u00b3\3\2\2\2$\u00bd\3\2\2\2&\u00bf\3\2\2\2(\u00ca\3\2\2\2*\u00cc\3\2"+
		"\2\2,\u00ce\3\2\2\2.\u00d8\3\2\2\2\60\u00da\3\2\2\2\62\u00dd\3\2\2\2\64"+
		"\66\5\4\3\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2"+
		"\2\29\67\3\2\2\2:;\7\2\2\3;\3\3\2\2\2<>\7\16\2\2=<\3\2\2\2=>\3\2\2\2>"+
		"?\3\2\2\2?H\5\6\4\2@B\7\16\2\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2"+
		"DE\3\2\2\2EG\5\6\4\2FA\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IL\3\2\2\2"+
		"JH\3\2\2\2KM\7\16\2\2LK\3\2\2\2LM\3\2\2\2M\5\3\2\2\2NP\5\b\5\2OQ\5\20"+
		"\t\2PO\3\2\2\2PQ\3\2\2\2Q\7\3\2\2\2RS\b\5\1\2ST\7\20\2\2TU\5\b\5\2UV\7"+
		"\21\2\2V[\3\2\2\2W[\5\n\6\2XY\7&\2\2Y[\5\b\5\5ZR\3\2\2\2ZW\3\2\2\2ZX\3"+
		"\2\2\2[d\3\2\2\2\\]\f\4\2\2]^\7\35\2\2^c\5\b\5\5_`\f\3\2\2`a\7)\2\2ac"+
		"\5\b\5\4b\\\3\2\2\2b_\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2e\t\3\2\2\2"+
		"fd\3\2\2\2gh\5\16\b\2hi\5\32\16\2ij\5\f\7\2jp\3\2\2\2kl\5\16\b\2lm\5\34"+
		"\17\2mn\t\2\2\2np\3\2\2\2og\3\2\2\2ok\3\2\2\2p\13\3\2\2\2qv\5\36\20\2"+
		"rv\5 \21\2sv\5&\24\2tv\5.\30\2uq\3\2\2\2ur\3\2\2\2us\3\2\2\2ut\3\2\2\2"+
		"v\r\3\2\2\2wx\5,\27\2x\17\3\2\2\2yz\5\30\r\2z{\5\22\n\2{\21\3\2\2\2|\u0081"+
		"\5\24\13\2}~\7\22\2\2~\u0080\5\24\13\2\177}\3\2\2\2\u0080\u0083\3\2\2"+
		"\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\23\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0084\u0086\5\26\f\2\u0085\u0087\t\3\2\2\u0086\u0085\3\2\2\2"+
		"\u0086\u0087\3\2\2\2\u0087\25\3\2\2\2\u0088\u0089\5,\27\2\u0089\27\3\2"+
		"\2\2\u008a\u008b\7*\2\2\u008b\u008c\7 \2\2\u008c\31\3\2\2\2\u008d\u009e"+
		"\7\23\2\2\u008e\u009e\7\33\2\2\u008f\u009e\7\25\2\2\u0090\u009e\7\26\2"+
		"\2\u0091\u009e\7\30\2\2\u0092\u009e\7\27\2\2\u0093\u009e\7\31\2\2\u0094"+
		"\u009e\7\32\2\2\u0095\u009e\7$\2\2\u0096\u0097\7&\2\2\u0097\u009e\7$\2"+
		"\2\u0098\u009e\7,\2\2\u0099\u009a\7,\2\2\u009a\u009e\7&\2\2\u009b\u009c"+
		"\7!\2\2\u009c\u009e\7+\2\2\u009d\u008d\3\2\2\2\u009d\u008e\3\2\2\2\u009d"+
		"\u008f\3\2\2\2\u009d\u0090\3\2\2\2\u009d\u0091\3\2\2\2\u009d\u0092\3\2"+
		"\2\2\u009d\u0093\3\2\2\2\u009d\u0094\3\2\2\2\u009d\u0095\3\2\2\2\u009d"+
		"\u0096\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u0099\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\33\3\2\2\2\u009f\u00a3\7%\2\2\u00a0\u00a1\7%\2\2\u00a1\u00a3"+
		"\7&\2\2\u00a2\u009f\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\35\3\2\2\2\u00a4"+
		"\u00ab\7h\2\2\u00a5\u00ab\7g\2\2\u00a6\u00ab\5*\26\2\u00a7\u00ab\5,\27"+
		"\2\u00a8\u00ab\5.\30\2\u00a9\u00ab\5\62\32\2\u00aa\u00a4\3\2\2\2\u00aa"+
		"\u00a5\3\2\2\2\u00aa\u00a6\3\2\2\2\u00aa\u00a7\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00aa\u00a9\3\2\2\2\u00ab\37\3\2\2\2\u00ac\u00ad\7g\2\2\u00ad\u00af"+
		"\7\20\2\2\u00ae\u00b0\5\"\22\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2"+
		"\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\21\2\2\u00b2!\3\2\2\2\u00b3\u00b8\5"+
		"$\23\2\u00b4\u00b5\7\22\2\2\u00b5\u00b7\5$\23\2\u00b6\u00b4\3\2\2\2\u00b7"+
		"\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9#\3\2\2\2"+
		"\u00ba\u00b8\3\2\2\2\u00bb\u00be\5\36\20\2\u00bc\u00be\5 \21\2\u00bd\u00bb"+
		"\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be%\3\2\2\2\u00bf\u00c0\7\20\2\2\u00c0"+
		"\u00c5\5\36\20\2\u00c1\u00c2\7\22\2\2\u00c2\u00c4\5\36\20\2\u00c3\u00c1"+
		"\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c8\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7\21\2\2\u00c9\'\3\2\2"+
		"\2\u00ca\u00cb\t\4\2\2\u00cb)\3\2\2\2\u00cc\u00cd\7#\2\2\u00cd+\3\2\2"+
		"\2\u00ce\u00cf\t\5\2\2\u00cf-\3\2\2\2\u00d0\u00d2\5\60\31\2\u00d1\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d9\3\2\2\2\u00d5\u00d9\7\13\2\2\u00d6\u00d9\7\t\2\2\u00d7\u00d9\7"+
		"\n\2\2\u00d8\u00d1\3\2\2\2\u00d8\u00d5\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d7\3\2\2\2\u00d9/\3\2\2\2\u00da\u00db\5\62\32\2\u00db\u00dc\t\6\2"+
		"\2\u00dc\61\3\2\2\2\u00dd\u00de\7\f\2\2\u00de\63\3\2\2\2\30\67=CHLPZb"+
		"dou\u0081\u0086\u009d\u00a2\u00aa\u00af\u00b8\u00bd\u00c5\u00d3\u00d8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}