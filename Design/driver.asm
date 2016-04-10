; driver program
; begin at 2000h

mvi a, 00h
mov b, a
out 98h; select channel 0
mvi a, 08
ora b
out 98h; send start of conversion
label1: in 0a8h
ani 01h
jz label1; check for end of conversion
in 90h; read from channel 0
sta 2500h; store read value
mvi a, 01h
mov b, a
out 98h; select channel 1
mvi a, 08h
ora b
out 98h; send start of conversion
label2: in 0a8h
ani 01h
jz label2; check for end of conversion
in 90h; read from channel 1
sta 2501h; store read value
call 2100h; call procedure to display potentiometer readings
call 2200h; call procedure to translate voltages to coordinates
call 2300h; call procedure to display translated coordinates
jmp 2000h; loop driver
