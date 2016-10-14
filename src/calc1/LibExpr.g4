grammar LibExpr; // Rename to distinguish from original

import CommonLexerRules; // includes all rules from CommonLexerRules.g4

prog: stat+ ;

stat
:
	expr NEWLINE # printExpr
	| ID '=' expr NEWLINE # assign
	| NEWLINE # blank
;

expr
:
	expr op =
	(
		'*'
		| '/'
	) expr # MulDiv
	| expr op =
	(
		'+'
		| '-'
	) expr # AddSub
	| expr '^' <assoc=right> expr #pow
	| INT # int
	| ID # id
	| '(' expr ')' # parens
;