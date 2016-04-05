#include <stdio.h>

int main(int argc, char **argv) {
	FILE *fp;
	char buf[1024];
	while(1) {
		printf("> ");
		scanf("%1023[^;];", buf);
		fp = popen(buf, "r");
		if(fp == NULL)
			return 1;
		while(fgets(buf, sizeof(buf) - 1, fp) != NULL)
			printf("%s", buf);
		pclose(fp);
	}
	return 0;
}
