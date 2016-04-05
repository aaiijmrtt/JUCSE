#!/bin/bash

function list {
	echo "[[ $1 d+f ]]" `find $1 -mindepth 1 -maxdepth 1 | wc -l`
	echo "[[ $1 d ]]" `find $1 -mindepth 1 -maxdepth 1 -type d | wc -l`
	echo "[[ $1 f ]]" `find $1 -mindepth 1 -maxdepth 1 -type f | wc -l`
}

list $1
for subdir in `find $1 -mindepth 1 -maxdepth 1 -type d`;
do
	list $subdir
done
