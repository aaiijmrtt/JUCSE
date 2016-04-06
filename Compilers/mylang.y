%{
void yyerror(char *s);
#include <stdio.h>
#include <stdlib.h>
%}

%start program
%token NUMBER
%token ID
%token WHITESPACE

%%

empty	:
	;
atom	:	ID { printf("atom -> ID\n"); }
	|	NUMBER { printf("atom -> NUMBER\n"); }
	;
members	:	program members { printf("member -> program members\n"); }
	| empty { printf("members -> empty\n"); }
	;
list	: '(' members ')' { printf("list -> ( members )\n"); }
	;
program	: atom { printf("program -> atom\n"); }
	| list { printf("program -> list\n"); }
	;

%%

int main (void) {
	return yyparse();
}

void yyerror(char *s) {
	fprintf(stderr, "%s\n", s);
}
