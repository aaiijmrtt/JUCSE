%include "util.mac"
extern buffer, array, inprompt, newline, arraysize, inpromptsize, newlinesize, outprompt, outpromptsize, inputarray, printarray

section .bss
	index: resb 4

section .text
	global _start

_start:
	call inputarray
	write inprompt, inpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	mov bh, [arraysize]
	call select
	write outprompt, outpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	exit

select:
	pusha
	mov ecx, 0
	mov cl, [arraysize]	
	dec ecx
	mov edx, 0
outer:
	push cx
	mov al, [array + edx]
	mov ebx, 0
	mov [index], ebx
	inc ebx
inner:
	mov ah, [array + edx + ebx]
	cmp al, ah
	jb continue
	mov [index], ebx
	mov al, ah
continue:
	inc ebx
	loop inner
	mov ebx, [index]
	mov al, [array + edx]
	mov ah, [array + edx + ebx]
	mov [array + edx], ah
	mov [array + edx + ebx], al
	call printarray
	write newline, newlinesize
	pop cx
	inc edx
	loop outer
	popa
	ret
