#include<stdio.h>
#include<stdlib.h>

int fib(int n) {
	int a=1,b=1,c,i;
	for(i=2;i<n;++i)
		c=a+b,a=b,b=c;
	return b;
}

int main(int argc,char *argv[]) {
	if(argc!=2)	{
		printf("[USAGE] %s [int]\n",argv[0]);
		exit(-1);
	}
	int n=atoi(argv[1]);
	printf("[FIBONACCI(%d)] %d\n",n,fib(n));
	return 0;
}
