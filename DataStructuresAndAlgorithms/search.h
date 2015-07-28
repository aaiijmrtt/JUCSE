#ifndef SEARCH_H
#define SEARCH_H

#define MORE 1
#define EQUAL 0
#define LESS -1

#define NOT_FOUND -1

typedef struct {
	char *ptr;
} str;

int compare_int(void *,void *);
int compare_float(void *,void *);
int compare_str(void *,void *);
int search_linear(void *,void *,int,int,int (*)(void *,void *));
int search_binary(void *,void *,int,int,int (*)(void *,void *));

#endif
