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
	call bubble
	write outprompt, outpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	exit

bubble:
	pusha
	mov ecx, 0
	mov cl, bh
	dec cl
outer:
	push cx  
	mov ebx, 0
inner:
	mov al, [array + ebx]
	mov ah, [array + ebx + 1]
	cmp al, ah
	jb continue
	mov [array + ebx], ah
	mov [array + ebx + 1], al
continue:
	inc ebx
	call printarray
	write newline, newlinesize
	loop inner
	pop cx
	loop outer
	popa
	ret
