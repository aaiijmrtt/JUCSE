#include<stdio.h>
#include<stdlib.h>

typedef struct node { int data; struct node* next; } node;
typedef struct { node* head; } list;

void printnode(node *oldnode) {
	printf("%d\t",oldnode->data);
}

void printlist(list *oldlist) {
	node *newnode=oldlist->head;
	while(newnode) {
		printnode(newnode);
		newnode=newnode->next;
	}
	printf("\n");
}

list *create() {
	list *newlist=(list *)malloc(sizeof(list));
	newlist->head=0;
	return newlist;
}

node *insertafter(node *oldnode, node *newnode) {
	newnode->next=oldnode->next;
	oldnode->next=newnode;
	return oldnode;
}

node *insertbefore(node *oldnode, node *newnode) {
	insertafter(oldnode,newnode);
	int temp=newnode->data;
	newnode->data=oldnode->data;
	oldnode->data=temp;
	return oldnode;
}

node *find(list *oldlist,int data) {
	node *newnode=oldlist->head;
	while(newnode&&(newnode->data!=data))
		newnode=newnode->next;
	return newnode;
}

list *inserthead(list *oldlist, node *newnode) {
	newnode->next=oldlist->head;
	oldlist->head=newnode;
	return oldlist;
}
