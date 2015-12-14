%include "util.mac"
extern buffer, newline, newlinesize, outprompt, outpromptsize

section .data
	ucprompt: db 'ENTER UPPERCASE: '
	lcprompt: db 'ENTER LOWERCASE: '
	cpromptsize: equ 17

section .text
	global _start

_start:
	write ucprompt, cpromptsize
	read buffer, 1
	add byte [buffer], 32
	write newline, newlinesize
	write outprompt, outpromptsize
	write buffer, 1
	write newline, newlinesize
	write lcprompt, cpromptsize
	read buffer, 1
	sub byte [buffer], 32
	write newline, newlinesize
	write outprompt, outpromptsize
	write buffer, 1
	write newline, newlinesize
	exit
