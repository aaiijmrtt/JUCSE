%include "util.mac"
extern buffer, newline, newlinesize, inputnumber, inputnumbersize, outprompt, outpromptsize, getnumber

section .text
	global _start
gcd:
	div bl
	and ah, ah
	jz end
	mov al, bl
	mov bl, ah
	mov ah, 0
	call gcd
end:
	ret

_start:
	write inputnumber, inputnumbersize
	call getnumber
	write newline, newlinesize
	mov eax, 0
	mov al, [buffer]
	write inputnumber, inputnumbersize
	call getnumber
	write newline, newlinesize
	mov ebx, 0
	mov bl, [buffer]
	call gcd
	write outprompt, outpromptsize
	printword bx, buffer, 2
	write newline, newlinesize
	exit
