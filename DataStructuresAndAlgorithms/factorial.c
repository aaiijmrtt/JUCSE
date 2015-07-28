#include<stdio.h>
#include<stdlib.h>

#include"lint.h"

lint *_factorialI(lint *n) {
	lint *fac=cloneI(n),*one=convertI("1"),*mult=_subtractionI(n,one),*tmp;
	while(_compareI(mult,one)!=EQUAL) {
		tmp=_kmultiplicationI(fac,mult);
		deleteI(fac);
		fac=tmp;
		tmp=_subtractionI(mult,one);
		deleteI(mult);
		mult=tmp;
		tmp=shrinkI(mult);
		deleteI(mult);
		mult=tmp;
	}
	deleteI(one);
	deleteI(mult);
	return fac;
}

int main(int argc,char **argv) {
	if(argc!=2)	{
		printf("[USAGE] %s [lint]\n",*argv);
		exit(-1);
	}
	lint *n=convertI(*(argv+1)),*fac=_factorialI(n);
	printI(n);
	printf("!=");
	printI(fac);
	printf("\n");
	deleteI(n);
	deleteI(fac);
	return 0;
}
