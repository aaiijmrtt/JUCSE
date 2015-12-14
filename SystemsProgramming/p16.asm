%include "util.mac"
extern buffer, array, inprompt, newline, arraysize, inpromptsize, newlinesize, inputarray, printarray

section .data
	outprompt1: db 'LOWER: '
	outprompt2: db 'UPPER: '
	outpromptsize: equ 7

section .bss
	lower: resb 1
	upper: resb 1

section .text
	global _start

_start:
	call inputarray
	write inprompt, inpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	mov byte [lower], 20
	mov byte [upper], 40
	mov al, 2
	call count
	mov eax, 0
	mov al, bh
	write outprompt1, outpromptsize
	printword ax, buffer, 1
	write newline, newlinesize
	mov al, bl
	write outprompt2, outpromptsize
	printword ax, buffer, 1
	write newline, newlinesize
	exit

count:
	mov ebx, 0
	mov ecx, 0
	mov cl, [arraysize]
continue:
	mov al, [array + ecx - 1]
	cmp [lower], al
	jb skip1
	inc bh
skip1:
	cmp al, [upper]
	jb skip2
	inc bl
skip2:
	loop continue
end:
	ret
