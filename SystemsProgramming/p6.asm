%include "util.mac"
extern buffer, store, newline, newlinesize, outprompt, outpromptsize

section .data
	primes: db 2, 3, 5, 7
	inprompt: db 'ENTER DIGIT: '
	primesize: equ 4
	ipromptsize: equ 13

section .text
	global _start

check:
	mov ecx, primesize
again:
	xor bl, [primes + ecx - 1]
	jz end
	xor bl, [primes + ecx - 1]
	loop again
end:
	ret

_start:
	write inprompt, ipromptsize
	read buffer, 1
	write newline, newlinesize
	mov bl, [buffer]
	sub bl, 30h
	mov [store], bl
	call check
	and bl, bl
	jnz ending
	write inprompt, ipromptsize
	read buffer, 1
	write newline, newlinesize
	mov bl, [buffer]
	sub bl, 30h
	mov ah, bl
	call check
	and bl, bl
	jnz ending
	mov al, [store]
	mul ah
	mov [store], al
	write outprompt, outpromptsize
	mov al, [store]
	printword ax, buffer, 2
	write newline, newlinesize
ending:
	exit
