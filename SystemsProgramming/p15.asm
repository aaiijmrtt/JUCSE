%include "util.mac"
extern buffer, store, array, inprompt, newline, arraysize, inpromptsize, newlinesize, getnumber, inputarray, printarray

section .data
	midprompt: db 'INPUT ELEMENT: '
	outprompt: db 'INDEX: '
	oopsprompt: db 'ELEMENT NOT FOUND'
	mpromptsize: equ 15
	outpromptsize: equ 7
	oopspromptsize: equ 17

section .text
	global _start

_start:
	call inputarray
	write inprompt, inpromptsize
	write newline, newlinesize
	call printarray
	write newline, newlinesize
	write midprompt, mpromptsize
	write newline, newlinesize
	call getnumber
	write newline, newlinesize
	mov eax, 0
	mov al, [buffer]
	mov [store], al
	call binary
	cmp al, -1
	je notfound
	write outprompt, outpromptsize
	write newline, newlinesize
	printword ax, buffer, 1
	write newline, newlinesize
	exit
notfound:
	write oopsprompt, oopspromptsize
	write newline, newlinesize
	exit	

binary:
	mov ebx, 1
	mov ecx, 0
	mov cl, [arraysize]
continue:
	cmp ecx, ebx
	jb none
	mov eax, 0
	mov eax, ebx
	add eax, ecx
	shr eax, 1
	mov dl, [array + eax - 1]
	cmp [store], dl
	jz equal
	jb lower
	inc eax
	mov ebx, eax
	jmp continue
lower:
	dec eax
	mov ecx, eax
	jmp continue
none:
	mov eax, -1
equal:
	ret
