_first = dict()
_follow = dict()
_table = dict()
_endsymbol = '$'
_emptysymbol = '#'

# function to remove left recursion from a subgrammar
def recursion(grammar):
	section = grammar[0][0]
	nonrecursivegrammar = [[item for item in rule] + [section + 'bar'] for rule in grammar if rule[0] != rule[1]]
	recursivegrammar = [rule for rule in grammar if rule[0] == rule[1]]
	return [rule for rule in nonrecursivegrammar] + [[section + 'bar', _emptysymbol]] + [[rule[0] + 'bar'] + rule[2: ] + [section + 'bar'] for rule in recursivegrammar]

# function to left factor a subgrammar
def factoring(grammar):
	section = grammar[0][0]
	factoredgrammar = list()
	index = 0
	while index + 1 < len(grammar):
		mismatches = [subindex for subindex in range(min(len(grammar[index]), len(grammar[index + 1]))) if grammar[index][subindex] != grammar[index + 1][subindex]]
		maxmatch = min(mismatches) if mismatches else min(len(grammar[index]), len(grammar[index + 1]))
		if maxmatch == 1:
			index += 1
			continue
		subindex = 2
		while index + subindex < len(grammar):
			if grammar[index][: maxmatch] != grammar[index + subindex][: maxmatch]:
				break
			subindex += 1
		factoredgrammar.append(grammar[index][: maxmatch] + [section + 'star' * (index + 1)])
		for subsubindex in range(subindex):
			if grammar[index + subsubindex][maxmatch: ]:
				factoredgrammar.append([section + 'star' * (index + 1)] + grammar[index + subsubindex][maxmatch: ])
			else:
				factoredgrammar.append([section + 'star' * (index + 1)] + [_emptysymbol])
		del grammar[index : index + subindex]
	return factoredgrammar + grammar

# function to calculate first of expressions
def first(expression, grammar, terminals):
	if expression in _first:
		return _first[expression]
	if expression in terminals:
		return set((expression, ))
	_first[expression] = set()
	for rule in grammar:
		if expression == rule[0]:
			flag = True
			for production in rule[1: ]:
				foremost = first(production, grammar, terminals)
				_first[expression].update(foremost)
				if _emptysymbol in foremost:
					_first[expression].remove(_emptysymbol)
				else:
					flag = False
					break
			if flag:
				_first[expression].add(_emptysymbol)
	return _first[expression]

# function to calculate follow of expressions
def follow(expression, grammar, terminals, startsymbol):
	if expression in _follow:
		return _follow[expression]
	temporary = frozenset()
	if expression == startsymbol:
		temporary = temporary.union(frozenset([_endsymbol]))
	for rule in grammar:
		if expression in rule[1: ]:
			index = rule[1: ].index(expression)
			index += 2
			flag = True
			for production in rule[index: ]:
				foremost = first(production, grammar, terminals)
				temporary = temporary.union(foremost)
				if _emptysymbol in foremost:
					temporary = temporary.difference(frozenset([_emptysymbol]))
				else:
					flag = False
					break
			if flag and rule[0] != expression:
				temporary = temporary.union(follow(rule[0], grammar, terminals, startsymbol))
	_follow[expression] = temporary
	return _follow[expression]

# function to create parsing table
def table(grammar, terminals, startsymbol):
	for rule in grammar:
		if rule[0] not in _table:
			_table[rule[0]] = dict()
			for terminal in terminals:
				_table[rule[0]][terminal] = set()
			_table[rule[0]][_endsymbol] = set()
	for rule in grammar:
		flag = True
		for production in rule[1: ]:
			foremost = first(production, grammar, terminals)
			for symbol in foremost:
				if symbol in terminals or symbol == _endsymbol:
					_table[rule[0]][symbol].add(tuple(rule[1: ]))
			if _emptysymbol not in foremost:
				flag = False
				break
		if flag:
			rearmost = follow(rule[0], grammar, terminals, startsymbol)
			for symbol in rearmost:
				if symbol in terminals or symbol == _endsymbol:
					_table[rule[0]][symbol].add(tuple(rule[1: ]))
			if _endsymbol in rearmost:
				_table[rule[0]][_endsymbol].add(tuple(rule[1: ]))
	return _table

# function to syntactically parse code
def parser(code, start):
	_input = line.strip().split() + [_endsymbol]
	_stack = [_endsymbol, start]

	while _stack:
		if not _input or _stack[-1] == _emptysymbol:
			print '[?POP', _stack.pop(), ']', _input, _stack
			continue
		if _stack[-1] == _input[0]:
			print '[POP', _stack.pop(), ']', _input, _stack
			_input = _input[1: ]
			continue
		if not _table[_stack[-1]][_input[0]]:
			print '[!POP', _stack.pop(), ']', _input, _stack
			continue
		ex = list(_table[_stack[-1]][_input[0]])[0]
		a = _stack.pop()
		_stack += [x for x in reversed(ex)]
		print '[RULE', a, '->', ' '.join(ex), ']', _input, _stack

# function to replace rules while removing indirect left recursion
def replace(replacable, replacing):
	replacedgrammar = list()
	for replacablerule in replacable:
		flag = False
		for replacingrule in replacing:
			for index in range(1, len(replacablerule)):
				if replacablerule[index] == replacingrule[0]:
					replacedgrammar.append(replacablerule[: index] + replacingrule[1: ] + replacablerule[index + 1:])
					flag = True
		if not flag:
			replacedgrammar.append(replacablerule)
	return replacedgrammar

if __name__ == '__main__':
	grammar = [rule.split() for rule in open('grammar.txt', 'r').readlines()]
	grammar = [[rule[0]] + rule[2: ] for rule in grammar]
	temporarygrammar = list()
	index = 0

	while index < len(grammar):
		subindex = 1
		while index + subindex < len(grammar):
			if grammar[index][0] != grammar[index + subindex][0]:
				break
			subindex += 1
		temporarygrammar = temporarygrammar + factoring(grammar[index: index + subindex])
		index += subindex

	grammar = temporarygrammar
	temporarygrammar = list()
	index = 0

	while index < len(grammar):
		subindex = 1
		while index + subindex < len(grammar):
			if grammar[index][0] != grammar[index + subindex][0]:
				break
			subindex += 1
		temporarygrammar = temporarygrammar + recursion(replace(grammar[index: index + subindex], temporarygrammar))
		index += subindex

	grammar = temporarygrammar
	startsymbol = 'program'
	print '\n\nGRAMMAR RULES\n\n'
	for rule in grammar:
		print rule

	terms = set([term for rule in grammar for term in rule])
	nonterminals = set([rule[0] for rule in grammar])
	terminals = terms - nonterminals

	print '\n\nFIRSTS\n\n'
	for nonterminal in nonterminals:
		print nonterminal, first(nonterminal, grammar, terminals)

	print '\n\nFOLLOWS\n\n'
	for nonterminal in nonterminals:
		print nonterminal, follow(nonterminal, grammar, terminals, startsymbol)

	table(grammar, terminals, startsymbol)

	print '\n\nTABLE\n\n'
	for left in _table:
		for top in _table[left]:
			if _table[left][top]:
				print left, top, _table[left][top]

	with open('intermediate.txt', 'r') as filein:
		for line in filein:
			print '\n\nSYNTACTIC PARSE\n\n'
			parser(line.strip().split(), startsymbol)
