%include "util.mac"
extern buffer, store, newline, newlinesize, outprompt, outpromptsize

section .text
	global _start

fibo:
	mov eax, 1
	mov ebx, 1
label:
	add eax, ebx
	xchg eax, ebx
	loop label
	ret

_start:
	write outprompt, outpromptsize
	write newline, newlinesize
	mov byte [store], 1
again:
	mov ecx, 0
	mov cl, byte [store]
	call fibo
	printword ax, buffer, 3
	write newline, newlinesize
	mov cl, byte [store]
	inc cl
	mov [store], cl
	cmp cl, 16
	jnz again
	exit
