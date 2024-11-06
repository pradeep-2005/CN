%{
#include <stdio.h>
%}

%%

[A-Z]    { printf("Capital letter: %s\n", yytext); }
\n       { /* Ignore newline */ }
.        { /* Ignore other characters */ }

%%

int main() {
    yylex();  // Start the lexer
    return 0;
}
