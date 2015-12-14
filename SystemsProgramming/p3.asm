%include "util.mac"
extern buffer, store, newline, newlinesize

section .text
	global _start

_start:
	mov ecx, 26
again:
	mov [buffer], byte 40h
	add [buffer], cl
	mov [store], cl
	write buffer, 1
	mov cl, [store]
	loop again
	write newline, newlinesize
	exit
