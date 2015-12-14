%include "util.mac"
extern buffer, array, newline, newlinesize, outprompt, outpromptsize, inputarray

section data
	maxprompt: db 'MAXIMUM: '
	minprompt: db 'MINIMUM: '
	mpromptsize: equ 9

section .bss
	max: resb 1
	min: resb 1

section .text
	global _start

_start:
	call inputarray
	mov byte [max], 0
	mov byte [min], 255
	mov ecx, 9
next:
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
	loop next
	mov al, [max]
	mov ah, 0
	write maxprompt, mpromptsize
	printword ax, buffer, 2
	write newline, newlinesize
	mov al, [min]
	mov ah, 0
	write minprompt, mpromptsize
	printword ax, buffer, 2
	write newline, newlinesize
	exit
