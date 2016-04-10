; procedure to translate voltages to coordinates using a lookup table
; begin at 2200h

lxi h, 2510h
mov a, m; load most significant 3 bits of potentiometer value
ral
ral
ral
mov b, a
inx h
mov a, m; load most significant 3 bits of potentiometer value
xra b
ral
mov l, a
mvi h, 24h; lookup table begins at 2400h
mov a, m; load translated x coordinate
sta 2512h; store translated x coordinate
inx h
mov a, m; load translated y coordinate
sta 2513h; store translated y coordinate
ret
