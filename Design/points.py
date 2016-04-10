# python program to generate point translation lookup table
# translate coordinates

import math

uV = 2.8
lV = 0.0
V = 5.0
uA = 180.0
lA = 0.0

address = 0x2400
for i in range(8):
	stored = list()
	for j in range(8):
		theta1 = i / 8.0 * V / uV * math.pi
		theta2 = j / 8.0 * V / uV * math.pi
		alpha = math.pi + theta1
		beta = theta2 + theta1
		x = 26.0 * (2.0 + math.cos(alpha) + math.cos(beta))
		y = 26.0 * (2.0 + math.sin(alpha) + math.sin(beta))
		stored += [hex(int(x)), hex(int(y))]
		address += 2
	print hex(address - 16), stored

x = [int(stored[2 * i], 16) for i in range(len(stored) / 2)]
y = [int(stored[2 * i + 1], 16) for i in range(len(stored) / 2)]

# plot points

import matplotlib.pyplot

matplotlib.pyplot.plot(x, y, 'ro')
matplotlib.pyplot.axis([0, 104, 0, 104])
matplotlib.pyplot.show()
