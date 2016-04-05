#!/bin/bash

echo $#
echo $8 $1 $2 $6 $7 $3 $4

echo $8 $1 $2 $6 $7 $3 $4 > file.txt
users >> file.txt
whoami >> file.txt
date +%d-%m-%Y >> file.txt
