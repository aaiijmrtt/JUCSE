%include "util.mac"
extern buffer, store, newline, newlinesize, outprompt, outpromptsize

section .data
	inprompt: db 'ENTER DIGIT: '
	name: db 'AMITRAJIT', 10
	ipromptsize: equ 13
	namesize: equ 10

section .text
	global _start

_start:
	write inprompt, ipromptsize
	read buffer, 1
	write newline, newlinesize
	write outprompt, outpromptsize
	mov al, [buffer]
	sub al, 30h
	mov [store], al
	mov eax, 0
again:
	mov al, [store]
	mov al, [name + eax]
	mov [buffer], al
	write buffer, 1
	mov al, [store]
	inc al
	mov [store], al
	cmp al, namesize
	jnz again
	exit
