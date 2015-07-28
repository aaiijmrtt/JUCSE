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

lint *stringtolint(char *str) {
	int i;
	lint *strint=createI(2*len(str));
	for(i=0;i<strint->length/2;++i) {
		strint->digits[2*i]=(str[i]-'A')%10;
		strint->digits[2*i+1]=(str[i]-'A')/10;
	}
	return strint;
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
	if(argc!=3)	{
		printf("[USAGE] %s [string] [int]\n",argv[0]);
		printf("[string]:CONVERSION STRING\n");
		printf("[int]:PRIME LOWER LIMIT\n");
		exit(-1);
	}
	int l=atoi(argv[2]);
	lint *prime=inttolint(prm(l));
	lint *strint=stringtolint(argv[1]);
	lint **split=_splitI(strint,(strint->length)/2);
	lint *sum=_additionI(*(split+1),*(split));
	lint **rem=_divisionI(sum,prime);
	printf("STR_INT[");
	printI(strint);
	printf("]\n");
	printf("PRIME[");
	printI(prime);
	printf("]\n");
	printf("SPLIT_LOW[");
	printI(*(split));
	printf("]\n");
	printf("SPLIT_HIGH[");
	printI(*(split+1));
	printf("]\n");
	printf("SUM[");
	printI(sum);
	printf("]\n");
	printf("REMAINDER[");
	printI(*(rem+1));
	printf("]\n");
	deleteI(prime);
	deleteI(strint);
	destroyI(split,2);
	deleteI(sum);
	destroyI(rem,2);
	return 0;
}
