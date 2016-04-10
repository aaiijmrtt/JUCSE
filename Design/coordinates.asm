s; procedure to display translated coordinates in address field
; begin at 2300h

lxi h, 2512h
mov a, m; load translated x coordinate
mov c, a
ani 0f0h
rar
rar
rar
rar
sta 2514h; store most significant 4 bits of translated x coordinate
mov a, c
ani 0fh
sta 2515h; store least significant 4 bits of translated x coordinate
inx h
mov a, m; load translated y coordinate
mov c, a
ani 0f0h
rar
rar
rar
rar
sta 2516h; store most significant 4 bits of translated y coordinate
mov a, c
ani 0fh
sta 2517h; store least significant 4 bits of translated y coordinate
mvi a, 00h; set parameter for address field
mvi b, 00h; set parameter for no dot
lxi h, 2514h; set parameter for address
call 05d0h; call routine to display characters
ret
