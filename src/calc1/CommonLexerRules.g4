lexer grammar CommonLexerRules; // note "lexer grammar"

MUL
:
	'*'
; // assigns token name to '*' used above in grammar

DIV
:
	'/'
;

ADD
:
	'+'
;

SUB
:
	'-'
;

ID
:
	[a-zA-Z]+
; // match identifiers

INT
:
	[0-9]+
; // match integers

NEWLINE
:
	'\r'? '\n'
; // return newlines to parser (end-statement signal)

WS //toss out whitespace

:
	[ \t]+ -> skip
; 