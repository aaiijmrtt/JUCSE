#include<stdio.h>
#include<stdlib.h>

int fib(int n) {
	if(n>>1)
		return fib(n-1)+fib(n-2);
	return n;
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
