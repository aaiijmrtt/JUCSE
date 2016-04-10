; procedure to display potentiometer readings in data field
; begin at 2100h

lxi h, 2500h
mov a, m; load potentiometer value
rar
rar
rar
rar
rar
sta 2510h; store most significant 3 bits
inx h
mov a, m; load potentiometer value
rar
rar
rar
rar
rar
sta 2511h; store most significant 3 bits
mvi a, 01h; set parameter for data field
mvi b, 00h; set parameter for no dot
lxi h, 2510h; set parameter for address
call 05d0h; call routine to display characters
ret
