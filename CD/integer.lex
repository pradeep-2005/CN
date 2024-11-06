%{
#include <stdio.h>

int integer_count = 0;
int constant_count = 0;
%}

%%

[0-9]+        { 
                integer_count++; 
                constant_count++; 
                printf("Integer constant: %s\n", yytext); 
              }

[0-9]+"."[0-9]+   { 
                    constant_count++; 
                    printf("Floating-point constant: %s\n", yytext); 
                  }

.                { /* Ignore other characters */ }
\n               { /* Ignore newlines */ }

%%

int main() {
    yylex();  // Start the lexer
    printf("Total number of integer constants: %d\n", integer_count);
    printf("Total number of numeric constants: %d\n", constant_count);
    return 0;
}
