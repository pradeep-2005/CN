%{
#include <stdio.h>

int letter_count = 0;
int character_count = 0;
%}

%%

[A-Za-z]   { letter_count++; character_count++; }  // Count letters and all characters
.          { character_count++; }                 // Count every character

\n         { character_count++; }                 // Count newline as a character

%%

int main() {
    yylex();  // Start the lexer
    printf("Total number of letters: %d\n", letter_count);
    printf("Total number of characters: %d\n", character_count);
    return 0;
}
