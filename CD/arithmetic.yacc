%{
#include "y.tab.h"  // Needed to share tokens with YACC
%}

%%

[0-9]+             { yylval = atoi(yytext); return NUMBER; }   // Recognize integer numbers
"("                { return LPAREN; }
")"                { return RPAREN; }
"+"                { return PLUS; }
"-"                { return MINUS; }
"*"                { return MULTIPLY; }
"/"                { return DIVIDE; }
[ \t\n]+           { /* Ignore whitespace */ }
.                  { printf("Invalid character: %s\n", yytext); }

%%
