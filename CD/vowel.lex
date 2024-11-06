%{
#include <stdio.h>

int vowel_count = 0;
int consonant_count = 0;
%}

%%

[aeiouAEIOU]   { vowel_count++; }              // Match vowels
[a-zA-Z]       { consonant_count++; }          // Match consonants (this includes vowels but we handle them separately)
\n             { /* Ignore newlines */ }
.              { /* Ignore other characters */ }

%%

int main() {
    printf("Enter a string: ");
    yylex();  // Start the lexer
    printf("Total number of vowels: %d\n", vowel_count);
    printf("Total number of consonants: %d\n", consonant_count - vowel_count); // Subtract vowels from consonants
    return 0;
}
