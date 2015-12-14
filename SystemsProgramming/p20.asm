%include "util.mac"
extern buffer, store, array, inprompt, newline, arraysize, inpromptsize, newlinesize, inputarray, printarray

section .data
	outprompt1: db '2ND MAXIMUM OUTPUT: '
	outprompt2: db '2ND MINIMUM OUTPUT: '
	outpromptsize: equ 20

section .bss
	max: resb 1
	min: resb 1
	nthmax: resb 1
	nthmin: resb 1

section .text
	global _start

_start:
	call inputarray
	write inprompt, inpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	mov byte [max], 0
	mov byte [nthmax], 255
	mov ecx, 0
	mov cl, [arraysize]
	push ecx
	call findnthmax
	pop ecx
	mov al, [max]
	mov [nthmax], al
	mov byte [max], 0
	push ecx
	call findnthmax
	pop ecx
	write outprompt1, outpromptsize
	mov al, [max]
	mov ah, 0
	printword ax, buffer, 2
	write newline, newlinesize
	mov byte [min], 255
	mov byte [nthmin], 0
	mov ecx, 0
	mov cl, [arraysize]
	push ecx
	call findnthmin
	pop ecx
	mov al, [min]
	mov [nthmin], al
	mov byte [min], 255
	push ecx
	call findnthmin
	pop ecx
	write outprompt2, outpromptsize
	mov al, [min]
	mov ah, 0
	printword ax, buffer, 2
	write newline, newlinesize
	exit

findnthmax:
	mov al, [nthmax]
	cmp [array + ecx - 1], al
	jnc skip1
	mov al, [max]
	cmp [array + ecx - 1], al
	jc skip1
	mov al, [array + ecx - 1]
	mov [max], al
skip1:
	loop findnthmax
	ret

findnthmin:
	mov al, [nthmin]
	cmp al, [array + ecx - 1]
	jnc skip2
	mov al, [min]
	cmp al, [array + ecx - 1]
	jc skip2
	mov al, [array + ecx - 1]
	mov [min], al
skip2:
	loop findnthmin
	ret
