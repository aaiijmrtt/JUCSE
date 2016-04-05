#!/bin/bash

if [[ ! -d CSE-52 ]]; then
	mkdir CSE-52
fi
ls -l | grep 'CSE-52'

chmod +r CSE-52
ls -l | grep 'CSE-52'

chmod 770 CSE-52
ls -l | grep 'CSE-52'

chmod 710 CSE-52
ls -l | grep 'CSE-52'
