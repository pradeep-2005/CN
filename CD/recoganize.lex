%{ 
#include <stdio.h> 
#include "y.tab.h"
%}

/* Define Patterns */
KEYWORD  "int"|"float"|"char"|"return"|"if"|"else"|"while"|"for"|"do"|"void"
IDENTIFIER [a-zA-Z_][a-zA-Z0-9_]*
NUMBER    [0-9]+(\.[0-9]+)?
OPERATOR  \+|\-|\*|\/|%
PUNCTUATION [,;(){}]

%% 

{KEYWORD}      { printf("Keyword: %s\n", yytext); }
{IDENTIFIER}   { printf("Identifier: %s\n", yytext); }
{NUMBER}       { printf("Number: %s\n", yytext); }
{OPERATOR}     { printf("Operator: %s\n", yytext); }
{PUNCTUATION}  { printf("Punctuation: %s\n", yytext); }
\n             { /* Ignore newline */ }
[ \t]          { /* Ignore whitespace */ }

%% 

int main() {
    yylex();  // Start the lexer
    return 0;
}
