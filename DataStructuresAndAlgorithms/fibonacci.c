#include<stdio.h>
#include<stdlib.h>

#include"lint.h"

lint *_fibonacciI(lint *n) {
	lint *i=convertI("2"),*a=convertI("1"),*b=convertI("1"),*c,*one=convertI("1"),*tmp;
	while(_compareI(i,n)==LESS) {
		c=_additionI(b,a);
		tmp=shrinkI(c);
		deleteI(c);
		c=tmp;
		deleteI(a);
		a=b;
		b=c;
		tmp=_additionI(i,one);
		deleteI(i);
		i=tmp;
		tmp=shrinkI(i);
		deleteI(i);
		i=tmp;
	}
	deleteI(i);
	deleteI(a);
	deleteI(one);
	return b;
}

int main(int argc,char **argv) {
	if(argc!=2)	{
		printf("[USAGE] %s [lint]\n",*argv);
		exit(-1);
	}
	lint *n=convertI(*(argv+1)),*fibo=_fibonacciI(n);
	printI(n);
	printf("F=");
	printI(fibo);
	printf("\n");
	deleteI(n);
	deleteI(fibo);
	return 0;
}
