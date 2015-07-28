#include<stdio.h>
#include<stdlib.h>

#define ALLENDING 2
#define ASCENDING 1
#define NONENDING 0
#define DESCENDING -1

int less(int a,int b) {
	return a<b;
}

int more(int a,int b) {
	return a>b;
}

int equal(int a,int b) {
	return a==b;
}

int *mergeSorted(int *a1,int l1,int *a2,int l2,int order,int (*compare)(int,int)) {
	int *a=(int *)malloc((l1+l2)*sizeof(int)),i=0,j=0,k=0;
	while(i<l1&&j<l2)
		if(compare(a1[i],a2[j])==order)
			a[k++]=a1[i++];
		else
			a[k++]=a2[j++];
	while(i<l1)
		a[k++]=a1[i++];
	while(j<l2)
		a[k++]=a2[j++];
	return a;
}

int sortOrder(int *a,int l) {
	int i,ca=0,cd=0;
	for(i=1;i<l;++i)
		if(a[i]<a[i-1])
			cd++;
		else if(a[i]>a[i-1])
			ca++;
	if(ca&&cd)
		return NONENDING;
	else if(ca)
		return ASCENDING;
	else if(cd)
		return DESCENDING;
	else
		return ALLENDING;
}
