%include "util.mac"
extern buffer, array, inprompt, newline, arraysize, inpromptsize, newlinesize, outprompt, outpromptsize, inputarray, printarray

section .text
	global _start

_start:
	call inputarray
	write inprompt, inpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	mov bh, [arraysize]
	call insert
	write outprompt, outpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	exit

insert:
	pusha
	mov edx, 1
outer:
	mov al, [array + edx]
	mov ecx, edx
inner:
	cmp [array + ecx - 1], al
	jbe skip
	mov ah, [array + ecx -1 ]
	mov [array + ecx], ah
	loop inner
skip:
	mov [array + ecx], al
	call printarray
	write newline, newlinesize
	inc dl
	cmp dl, [arraysize]
	jnz outer
	popa
	ret
