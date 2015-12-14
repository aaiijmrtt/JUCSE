%include "util.mac"
extern buffer, newline, newlinesize, outprompt, outpromptsize

section .data
	inprompt: db 'ENTER HEX: '
	array: db 30h, 31h, 32h, 33h, 34h, 35h, 36h, 37h, 38h, 39h, 41h, 42h, 43h, 44h, 45h, 46h
	ipromptsize: equ 11

section .text
	global _start

check:
	mov ecx, 16
again:
	xor bl, [array + ecx - 1]
	jz end
	xor bl, [array + ecx - 1]
	loop again
end:
	ret

_start:
	write inprompt, ipromptsize
	read buffer, 1
	write newline, newlinesize
	write outprompt, outpromptsize
	mov bl, [buffer]
	call check
	mov dx, cx
	dec dx
	printword dx, buffer, 2
	write newline, newlinesize
	exit
