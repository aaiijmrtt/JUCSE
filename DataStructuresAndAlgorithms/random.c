#include<stdio.h>
#include<stdlib.h>

int *gen(int n) {
	int i,*a=(int*)malloc(n*sizeof(int));
	for(i=0;i<n;++i)
		a[i]=i+1;
	return a;
}

int *ran(int n,int *a) {
	int r,i,t;
	srand(time(NULL));
	for(i=0;i<n;++i) {
		r=rand()%n;
		t=a[r];
		a[r]=a[i];
		a[i]=t;
	}
	return a;
}

void out(int n,int *a) {
	FILE *f=fopen("random.txt","w");
	int i;
	for(i=0;i<n;++i)
		fprintf(f,"%d\n",a[i]);
	fclose(f);
}

int main(int argc,char *argv[]) {
	if(argc!=2)	{
		printf("[USAGE] %s [int]\n",argv[0]);
		exit(-1);
	}
	int *a,n=atoi(argv[1]);
	a=gen(n);
	a=ran(n,a);
	out(n,a);
	return 0;
}
