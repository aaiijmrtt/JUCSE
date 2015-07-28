#include <iostream>
#include <stdexcept>
#include <limits>
using namespace std;
///class to store and manipulate STACK values
class STACK {
	int *base, ///<member to hold array of cars tolled
		head, ///<member to hold array of cars tolled
		size; ///<member to hold array of cars tolled
	public:
		///constructor to initialize data members and validate class invariants
		STACK(int sz=10) : base{new int[sz]}, head{0}, size{sz} {
			if(base == nullptr)
				throw invalid_argument("insufficient memory");
		}
		///member function to push element into stack
		void push(int el) {
			if(head == size)
				throw invalid_argument("stack overflow");
			*(base + head++) = el;
		}
		///member function to pop element from stack
		int pop() {
			if(head == 0)
				throw invalid_argument("stack underflow");
			return *(base + --head);
		}
};
int main() {
	int c, n;
	STACK stack;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> create a new stack" << endl
		<< "<2> push element into stack" << endl
		<< "<3> pop element from stack" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1:	cout << "enter stack size: ";
						if(!(cin >> n))  throw invalid_argument("arguments of invalid type");
						stack = STACK{n}; break;
				case 2: cout << "enter new element: ";
						if(!(cin >> n))  throw invalid_argument("arguments of invalid type");
						stack.push(n); break;
				case 3: cout << stack.pop() << endl; break;
				default: throw invalid_argument("arguments of invalid type");
			}
		}
		catch(invalid_argument e) {
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			cout << e.what() << ": try again" << endl;
		}
	}	while(c);
	return 0;
}
