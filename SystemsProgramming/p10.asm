%include "util.mac"
extern buffer, inputnumber, newline, inputnumbersize, newlinesize, outprompt, outpromptsize, getnumber

section .text
	global _start

_start:
	write inputnumber, inputnumbersize
	call getnumber
	write newline, newlinesize
	mov ecx, 0
	mov cl, [buffer]
	mov eax, 0
	mov al, 1
next:
	mul cl
	loop next
	write outprompt, outpromptsize
	printword ax, buffer, 3
	write newline, newlinesize
	exit
