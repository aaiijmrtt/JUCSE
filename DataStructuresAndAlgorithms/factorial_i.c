#include<stdio.h>
#include<stdlib.h>

int fac(int n) {
	int f=1;
	while(n>>1)
		f*=n--;
	return f;
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
