#include<stdio.h>
#include<stdlib.h>

int fac(int n) {
	if(n>>1)
		return n*fac(n-1);
	return 1;
}

int main(int argc,char *argv[]) {
	if(argc!=2)	{
		printf("[USAGE] %s [int]\n",argv[0]);
		exit(-1);
	}
	int n=atoi(argv[1]);
	printf("[FACTORIAL(%d)] %d\n",n,fac(n));
	return 0;
}
