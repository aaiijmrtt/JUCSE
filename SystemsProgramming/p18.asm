%include "util.mac"
extern buffer, store, newline, newlinesize

section .data
	inprompt: db 'ENTER 8 BITS: '
	outprompt: db 'OUTPUT: '
	array: db 30h, 31h, 32h, 33h, 34h, 35h, 36h, 37h, 38h, 39h, 41h, 42h, 43h, 44h, 45h, 46h
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
	mov ecx, 0
	mov cl, [store]
	shr ecx, 4
	mov cl, [array + ecx]
	mov [buffer], cl
	write buffer, 1
	mov cl, [store]
	and cl, 0fh
	mov cl, [array + ecx]
	mov [buffer], cl
	write buffer, 1
	write newline, newlinesize
	exit
