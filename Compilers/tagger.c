#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAXLENGTH 10

int main(int argc, char **argv) {
	char *lexeme = (char  *)malloc(MAXLENGTH * sizeof(char)),
		*POS = (char  *)malloc(MAXLENGTH * sizeof(char)),
		*token = (char  *)malloc(MAXLENGTH * sizeof(char)),
		*punctuator = (char  *)malloc(MAXLENGTH * sizeof(char));

	printf("[LEX]\t[POS]\t[LINE]\t[COLUMN]\n");
	FILE *input = fopen("input.txt", "r");
	int line = 0,
		column = 0;

	while(fscanf(input, "%10[^ ,.;:?!\t\n]%10[ ,.;:?!\t\n]", token, punctuator) != EOF) {
		FILE *lookup = fopen("lookup.txt", "r");
		while(fscanf(lookup, "%[^\t]\t%[^\n]\n", lexeme, POS) != EOF)
			if(!strcmp(token, lexeme))
				printf("%s\t%s\t%d\t%d\n", lexeme, POS, line, column);
		fclose(lookup);

		if(punctuator[0] == '\n') {
			column = 0;
			line++;
		}
		else
			column += strlen(token) + 1;
	}
	fclose(input);
	return 0;
}
