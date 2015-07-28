#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>

#include"lint.h"

lint *inttolint(int m) {
	int i,j;
	for(i=0,j=m;j>0;++i)
		j/=10;
	lint *n=createI(i);
	for(i=0;i<n->length;++i,m/=10)
		n->digits[i]=m%10;
	return n;
}

int prm(int n) {
	int temp,fac,sq;
	for(temp=n%2==0?n+1:n;1;temp+=2) {
		sq=sqrt(temp);
		for(fac=3;fac<=sq;fac+=2)
			if(temp%fac==0)
				break;
		if(fac>sq)
			return temp;
	}
}

int main(int argc,char *argv[])	{
	if(argc!=4)	{
		printf("[USAGE] %s [int1] [int2] [int3]\n",argv[0]);
		printf("[int1]:PRIME LOWER LIMIT\n");
		printf("[int2]:LIST SIZE LIMIT\n");
		printf("[int3]:LOG(10) NUMBER UPPER LIMIT\n");
		exit(-1);
	}
	int i,l=atoi(argv[1]),m=atoi(argv[2]),n=atoi(argv[3]);
	lint *prime=inttolint(prm(l)),**list=randlistI(m,n),**tmp;
	printf("[");
	printI(prime);
	printf("]\n");
	for(i=0;i<m;++i) {
		printI(*(list+i));
		printf(":\t");
		tmp=divisionI(*(list+i),prime);
		printI(*(tmp+1));
		printf("\n");
		destroyI(tmp,2);
	}
	destroyI(list,m);
	return 0;
}
