%include "util.mac"
extern buffer, store, array, inprompt, newline, arraysize, inpromptsize, newlinesize, inputarray, printarray

section .data
	outprompt1: db 'MAXIMUM OUTPUT: '
	outprompt2: db 'MINIMUM OUTPUT: '
	outpromptsize: equ 8

section .bss
	max: resb 1
	min: resb 1

section .text
	global _start

_start:
	call inputarray
	write inprompt, inpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	mov byte [max], 0
	mov byte [min], 255
	mov ecx, 0
	mov cl, [arraysize]
nextelement:
	mov al, [max]
	cmp [array + ecx - 1], al
	jc skip1
	mov al, [array + ecx - 1]
	mov [max], al
skip1:
	mov al, [min]
	cmp al, [array + ecx - 1]
	jc skip2
	mov al, [array + ecx - 1]
	mov [min], al
skip2:
	loop nextelement
	write outprompt1, outpromptsize
	mov al, [max]
	mov ah, 0
	printword ax, buffer, 2
	write newline, newlinesize
	write outprompt2, outpromptsize
	mov al, [min]
	mov ah, 0
	printword ax, buffer, 2
	write newline, newlinesize
	exit
