%include "util.mac"
extern buffer, store, newline, newlinesize

section .data
	inprompt: db 'ENTER 8 BITS: '
	outprompt: db 'OUTPUT: '
	inpromptsize: equ 14
	outpromptsize: equ 8

section .text
	global _start

_start:
	mov byte [store], 0
	write inprompt, inpromptsize
	mov ecx, 8
again:
	push ecx
	read buffer, 1
	mov al, [buffer]
	sub al, 30h
	mov bl, [store]
	rol bl, 1
	add bl, al
	mov [store], bl
	pop ecx
	loop again
	write newline, newlinesize
	write outprompt, outpromptsize
	mov eax, 0
	mov al, [store]
	printword ax, buffer, 3
	write newline, newlinesize
	exit
