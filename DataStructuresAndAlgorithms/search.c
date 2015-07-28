#define MORE 1
#define EQUAL 0
#define LESS -1

#define NOT_FOUND -1

typedef struct {
	char *ptr;
} str;

int compare_int(void *a,void *b) {
	if(*(int *)a>*(int *)b)
		return MORE;
	else if(*(int *)a==*(int *)b)
		return EQUAL;
	else
		return LESS;
}

int compare_float(void *a,void *b) {
	if(*(float *)a>*(float *)b)
		return MORE;
	else if(*(float *)a==*(float *)b)
		return EQUAL;
	else
		return LESS;
}

int compare_str(void *a,void *b) {
	int i;
	for(i=0;(*(((str *)a)->ptr+i)!='\0')&&(*(((str *)b)->ptr+i)!='\0');++i) {
		if(*(((str *)a)->ptr+i)!=*(((str *)b)->ptr+i))
			if(*(((str *)a)->ptr+i)>*(((str *)b)->ptr+i))
				return MORE;
			else
				return LESS;
	}
	if(*(((str *)a)->ptr+i)=='\0')
		if(*(((str *)b)->ptr+i)=='\0')
			return EQUAL;
		else
			return LESS;
	else
		return MORE;
}

int search_linear(void *element,void *array,
	int size,int length,
	int (*compare)(void *,void *)) {
	int i;
	for(i=0;i<length;++i)
		if(compare(element,array+i*size)==EQUAL)
			return i;
	return NOT_FOUND;
}

int search_binary(void *element,void *array,
	int size,int length,
	int (*compare)(void *,void *)) {
	int lo,mid,hi;
	for(lo=0,hi=length-1,mid=(lo+hi)/2;lo<=hi;mid=(lo+hi)/2)
		switch(compare(element,array+mid*size)) {
			case MORE:
				lo=mid+1;
			break;
			case EQUAL:
				return mid;
			case LESS:
				hi=mid-1;
			break;
		}
	return NOT_FOUND;
}
