_rightgroup = chr(200)
_leftgroup = chr(201)
_pipe = chr(202)
_dot = chr(203)
_star = chr(204)

# function to convert an infix expression to a postfix expression
def postfix(infix, vocabulary):
	pre = dict()
	pre[_star] = 3
	pre[_dot] = 2
	pre[_pipe] = 2
	pre[_leftgroup] = 1
	opStack = list()
	pfList = list()
	for token in infix:
		if token in vocabulary:
			pfList.append(token)
		elif token == _leftgroup:
			opStack.append(token)
		elif token == _rightgroup:
			top = opStack.pop()
			while top != _leftgroup:
				pfList.append(top)
				top = opStack.pop()
		else:
			while opStack and (pre[opStack[-1]] >= pre[token]):
				pfList.append(opStack.pop())
			opStack.append(token)
	while opStack:
		pfList.append(opStack.pop())
	return pfList

# function to convert a postfix expression to a Nondeterministic Finite Automata
def NFA(postfix, vocabulary):
	opStack = list()
	for token in postfix:
		if token == _star:
			op = opStack.pop()
			final = nNFA()
			op[1].terminal = False
			op[1].destination = op[0]
			op[1].alternative = final
			initial = nNFA(None, final, op[0])
			initial.terminal = False
			opStack.append((initial, final))
		elif token == _dot:
			op2 = opStack.pop()
			op1 = opStack.pop()
			op1[1].terminal = False
			op1[1].destination = op2[0]
			opStack.append((op1[0], op2[1]))
		elif token == _pipe:
			op2 = opStack.pop()
			op1 = opStack.pop()
			final = nNFA()
			op2[1].terminal = False
			op2[1].destination = final
			op1[1].terminal = False
			op1[1].destination = final
			initial = nNFA(None, op1[0], op2[0])
			initial.terminal = False
			opStack.append((initial, final))
		elif token in vocabulary:
			final = nNFA()
			initial = nNFA(token, final)
			initial.terminal = False
			opStack.append((initial, final))
	return opStack.pop()

# function to get the states of a Nondeterministic Finite Automata
def getstates(start, states = None):
	if not states:
		states = set()
	states.add(start)
	if start.destination and start.destination not in states:
		states.update(getstates(start.destination, states))
	if start.alternative and start.alternative not in states:
		states.update(getstates(start.alternative, states))
	return states

# function to get preexisting state if any
def newstate(states, unmarked, marked):
	for state in unmarked:
		if states == state.states:
			return state
	for state in marked:
		if states == state.states:
			return state
	return None

# function to convert a Nondeterministic Finite Automata to a Deterministic Finite Automata
def DFA(NFA, vocabulary):
	initial = nDFA(Eclosure([NFA[0]]))
	unmarked = [initial]
	marked = list()
	while unmarked:
		state = unmarked.pop()
		marked.append(state)
		if NFA[1] in state.states:
			state.terminal = True
		for action in vocabulary:
			states = nDFA(Eclosure(move(state.states, action)))
			if states:
				nstate = newstate(states.states, unmarked, marked)
				if nstate:
					states = nstate
				else:
					unmarked.append(states)
				state.transitions[action] = states
	return initial

# function to simulate transition
def move(states, action):
	moves = set()
	for state in states:
		if state.action == action:
			moves.add(state.destination)
	return moves

# function to calculate epsilon closure
def Eclosure(states):
	states = list(states)
	closure = [state for state in states]
	while states:
		state = states.pop()
		if not state.action:
			if state.destination and state.destination not in closure:
				closure.append(state.destination)
				states.append(state.destination)
			if state.alternative and state.alternative not in closure:
				closure.append(state.alternative)
				states.append(state.alternative)
	return closure

# function to parse using a Nondeterministic Finite Automata
def parseNFA(text, start):
	if start.terminal:
		return not text
	if not start.action:
		value = False
		if start.destination:
			value = value or parseNFA(text, start.destination)
		if start.alternative:
			value = value or parseNFA(text, start.alternative)
		return value
	if not text:
		return False
	if start.action == text[0]:
		value = False
		if start.destination:
			value = value or parseNFA(text[1: ], start.destination)
		if start.alternative:
			value = value or parseNFA(text[1: ], start.alternative)
		return value
	return False

# function to parse using a Deterministic Finite Automata
def parseDFA(text, start):
	if not text:
		return start.terminal
	if text[0] in start.transitions:
		return parseDFA(text[1: ], start.transitions[text[0]])
	return False

# function to parse text in code
def gettoken(dfas, text):
	for i in reversed(range(len(text))):
		for dfa in dfas:
			if parseDFA(text[: i + 1], dfa[1]):
				return text[: i + 1], text[i + 1: ], dfa[0]
	return '', text, None

# function to parse code
def parsecode(dfas, text, count = 0):
	parsedcode = list()
	while text:
		token, text, identifier = gettoken(dfas, text)
		if not token:
			parsedcode.append((None, text, None))
			return parsedcode, count
		parsedcode.append((count, token, identifier))
		count += len(token)
	return parsedcode, count

# class for Nondeterministic Finite Automata nodes
class nNFA:
	def __init__(self, action = None, destination = None, alternative = None):
		self.action = action
		self.destination = destination
		self.alternative = alternative
		self.terminal = True

# class for Deterministic Finite Automata nodes
class nDFA:
	def __init__(self, states):
		self.states = states
		self.transitions = dict()
		self.terminal = False

# function to lexically parse code
def lexer(code, vocabulary, regexs, priorities):
	dfas = list()
	for regex in priorities:
		dfas.append((regex, DFA(NFA(postfix(regexs[regex], vocabulary), vocabulary), vocabulary)))
	parsedcode = list()
	linecount = 0
	tokencount = 0
	for line in code.split('\n'):
		parse, count = parsecode(dfas, line, 0)
		parsedcode += [[linecount, item[0], item[1], item[2]] for item in parse]
		linecount += 1
		tokencount += count
	return parsedcode#, linecount, tokencount

# function to facilitate expression of regular expressions
def regex(units):
	return _leftgroup + (_rightgroup + _pipe + _leftgroup).join([_dot.join([subunit for subunit in unit]) for unit in units]) + _rightgroup

if __name__ == '__main__':
	vocabulary = 'abcdefghijklmmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_+-*/()[]{}|:;\\\'",.<>?=&^%$#@!~` \t\n'
	regexs = {
		'identifiers': _leftgroup + regex([x for x in 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_']) + _rightgroup + _dot + _leftgroup + regex([x for x in 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789']) + _rightgroup + _star,
		'operators': regex(['(', ')', '{', '}', '[', ']', ',', '.', '+', '-', '*', '/', '%', '++', '--', '==', '!=', '>', '<', '>=', '<=', '&&', '||', '!', '~', '&', '|', '<<', '>>', '^', '=', '+=', '-=', '*=', '/=', '%=', '<<=', '>>=', '&=', '^=', '|=', '?', ':', ';']),
		'numbers': _leftgroup + _leftgroup + regex([x for x in '0123456789']) + _rightgroup + _star + _rightgroup + _pipe + _leftgroup + '0' + _dot + _leftgroup + 'x' + _pipe + 'X' + _rightgroup + _dot + _leftgroup + regex([x for x in '0123456789']) + _rightgroup + _star + _rightgroup,
		'keywords': regex(['else', 'if', 'int', 'return', 'void', 'for']),
		'whitespace': _leftgroup + ' ' + _pipe + '\t' + _pipe + '\n' + _rightgroup + _star
	}
	priorities = ['keywords', 'identifiers', 'operators', 'numbers', 'whitespace']
	ignore = ['whitespace']
	verbatim = ['keywords', 'operators']

	with open('input.c', 'r') as filein:
		code = ''.join(filein.readlines())

	print '\n\nCODE\n\n'
	print code

	print '\n\nLEXICAL PARSE\n\n'
	parse = lexer(code, vocabulary, regexs, priorities)
	for item in parse:
		print item

	with open('intermediate.txt', 'w') as fileout:
		for item in parse:
			if item[3] in ignore:
				continue
			elif item[3] in verbatim:
				fileout.write(' ' + item[2])
			else:
				fileout.write(' ' + item[3])
