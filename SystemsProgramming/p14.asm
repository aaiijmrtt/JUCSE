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
	call linear
	mov ax, cx
	cmp ax, 0
	jz notfound
	write outprompt, outpromptsize
	write newline, newlinesize
	printword ax, buffer, 1
	write newline, newlinesize
	exit
notfound:
	write oopsprompt, oopspromptsize
	write newline, newlinesize
	exit	

linear:
	mov ecx, 0
	mov cl, [arraysize]
continue:
	mov al, [array + ecx - 1]
	cmp [store], al
	jz end
	loop continue
end:
	ret
