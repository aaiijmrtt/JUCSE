#include<stdio.h>
#include<stdlib.h>

typedef struct { int num, row, col; } data;
typedef struct node { data val; struct node *next; } node;

data make(int n,int r,int c) {
	data val;
	val.num=n;
	val.row=r;
	val.col=c;
	return val;
}

node *create(data val) {
	node *newnode=(node *)malloc(sizeof(node));
	newnode->val=val;
	newnode->next=0;
	return newnode;
}

node *insertafter(node *oldnode, node *newnode) {
	newnode->next=oldnode->next;
	oldnode->next=newnode;
	return newnode;
}

int find(node *list,data val) {
	if((list->val.row==val.row)&&(list->val.col==val.col))
		return list->val.num;
	if(((list->val.row>=val.row)&&(list->val.col>val.col))||(list->next==0))
		return 0;
	return find(list->next,val);
}

node *insertunchecked(node *list,data val) {
	if((list->val.row==val.row)&&(list->val.col==val.col)) {
		list->val.num=val.num;
		return list;
	}
	if((list->next==0)||((list->next->val.row>=val.row)&&(list->next->val.col>val.col)))
		return insertafter(list,create(val));
	return insertunchecked(list->next,val);
}

node *insert(node *list,data val) {
	if(val.num==0)
		return list;
	return insertunchecked(list,val);
}

node *inputmatrix() {
	int i,j,r,c,n;
	printf("enter rows, columns, and (rows*columns) elements: ");
	scanf("%d%d",&r,&c);
	node *list=create(make(r,-1,c));
	for(i=0;i<r;++i)
		for(j=0;j<c;++j) {
			scanf("%d",&n);
			insert(list,make(n,i,j));
		}
	return list;
}

void outputmatrix(node *list) {
	int i,j,r=list->val.num,c=list->val.col;
	for(i=0;i<r;++i) {
		for(j=0;j<c;++j)
			printf("%d\t",find(list,make(0,i,j)));
		printf("\n");
	}
}

node *addmatrix(node *m1,node *m2) {
	int i,j,r=m1->val.num,c=m2->val.col,n;
	node *list=create(make(r,-1,c));
	for(i=0;i<r;++i)
		for(j=0;j<c;++j) {
			n=find(m1,make(0,i,j))+find(m2,make(0,i,j));
			insert(list,make(n,i,j));
		}
	return list;
}

node *multiplymatrix(node *m1,node *m2) {
	int i,j,k,r=m1->val.num,c=m2->val.col,t=m1->val.col,n;
	node *list=create(make(r,-1,c));
	for(i=0;i<r;++i)
		for(j=0;j<c;++j) {
			for(n=k=0;k<t;++k)
				n+=find(m1,make(0,i,k))*find(m2,make(0,k,j));
			insert(list,make(n,i,j));
		}
	return list;
}
