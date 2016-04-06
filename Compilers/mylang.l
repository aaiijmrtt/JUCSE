%{
#include "y.tab.h"
%}

%%

[()]				return yytext[0];
[a-zA-Z]+			return ID;
[0-9]+				return NUMBER;
[ \t\n]				
.					fprintf(stderr, "[UNEXPECTED TOKEN]");

%%
int yywrap(void) {
	return 1;
}
