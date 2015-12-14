%include "util.mac"
extern inprompt, inpromptsize, buffer, store, newline, newlinesize, outprompt, outpromptsize

section .text
	global _start

_start:
	write inprompt, inpromptsize
	read buffer, 1
	mov al, [buffer]
	mov [store], al
	read buffer, 1
	write newline, newlinesize
	write outprompt, outpromptsize
	write buffer, 1
	mov al, [store]
	mov [buffer], al
	write buffer, 1
	write newline, newlinesize
	exit
