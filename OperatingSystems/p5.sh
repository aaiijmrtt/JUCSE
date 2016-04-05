#!/bin/bash

program='define fact(n) {
	if(n < 0)
		return -1;
	f = 1;
	for(; n > 1; --n)
		f *= n;
	return f;
}'
time echo $program fact\($1\) | bc
