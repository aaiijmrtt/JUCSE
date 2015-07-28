#include<stdio.h>
#include<stdlib.h>

#include"search.h"

void usage(char *name) {
	fprintf(stderr,"[USAGE] %s -[char(algo):l/b] -[char(type):i/f/s] [search for] [in array]\n",name);
	exit(-1);
}

int main(int argc,char *argv[]) {
	if(argc<5)
		usage(argv[0]);
	int index,i,length=argc-4;
	int element_int,*array_int;
	float element_float,*array_float;
	str element_str,*array_str;
	switch(argv[2][1]) {
		case 'i':
			element_int=atoi(argv[3]);
			array_int=(int *)malloc(length*sizeof(int));
			for(i=0;i<length;++i)
				array_int[i]=atoi(argv[i+4]);
			switch(argv[1][1]) {
				case 'l':
					index=search_linear(&element_int,array_int,
						sizeof(int),length,&compare_int);
					break;
				case 'b':
					index=search_binary(&element_int,array_int,
						sizeof(int),length,&compare_int);
					break;
				default:usage(argv[0]);
			}
			free(array_int);
			break;
		case 'f':
			element_float=atof(argv[3]);
			array_float=(float *)malloc(length*sizeof(float));
			for(i=0;i<length;++i)
				array_float[i]=atof(argv[i+4]);
			switch(argv[1][1]) {
				case 'l':
					index=search_linear(&element_float,array_float,
						sizeof(float),length,&compare_float);
					break;
				case 'b':
					index=search_binary(&element_float,array_float,
						sizeof(float),length,&compare_float);
					break;
				default:usage(argv[0]);
			}
			free(array_float);
			break;
		case 's':
			element_str.ptr=argv[3];
			array_str=(str *)malloc(length*sizeof(str));
			for(i=0;i<length;++i)
				array_str[i].ptr=argv[i+4];
			switch(argv[1][1]) {
				case 'l':
					index=search_linear(&element_str,array_str,
						sizeof(str),length,&compare_str);
					break;
				case 'b':
					index=search_binary(&element_str,array_str,
						sizeof(str),length,&compare_str);
					break;
				default:usage(argv[0]);
			}
			free(array_str);
			break;
		default:
			usage(argv[0]);
	}
	switch(index) {
		case NOT_FOUND:
			printf("NF\n");
			break;
		default:
			printf("AT %d\n",index);
	}
	return 0;
}
