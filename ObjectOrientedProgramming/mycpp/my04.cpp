#include<iostream>
#include<cstdlib>
#include<stdexcept>
#include<limits>
using namespace std;
///class to store and manipulate NODE values
class NODE {
	int value; ///<member to hold value of node
	NODE *prev, ///<member to hold pointer to previous node
		*next; ///<member to hold pointer to next node
	public:
		///constructor to initialize data members and validate class invariants
		NODE(int val=0) : value{val}, prev{nullptr}, next{nullptr} {
		}
		///setter function for value
		void setValue(int val) {
			value = val;
		}
		///getter function for value
		int getValue() {
			return value;
		}
		///getter function for previous node pointer
		NODE *getPrev() {
			return prev;
		}
		///getter function for next node pointer
		NODE *getNext() {
			return next;
		}
		///member function to insert newnode after this node
		NODE& insert(NODE& newnode) {
			newnode.next = next;
			newnode.prev = this;
			if(next != nullptr)
				next->prev = &newnode;
			next = &newnode;
			return newnode;
		}
		///member function to remove this node
		void remove() {
			if(prev != nullptr)
				prev->next = next;
			if(next != nullptr)
				next->prev = prev;
			this->~NODE();
		}
};
///class to store and manipulate LINKEDLIST values
class LINKEDLIST {
	NODE head; ///<member to hold head node of linked list
	public:
		///constructor to initialize data members and validate class invariants
		LINKEDLIST() : head{NODE{0}} {
		}
		///member function to output linked list
		void printList() {
			NODE *temp = head.getNext();
			for(int i = 0; i < head.getValue() ; ++i) {
				cout << temp->getValue() << '\t';
				temp = temp->getNext();
			}
			cout << endl;
		}
		///member function to insert an element in linked list
		void insert(int val, int pos) {
			if(pos > head.getValue() || pos < 0)
				throw invalid_argument("argument out of range");
			NODE *temp = &head, *newnode = new NODE{val};
			for(int i = 0; i < pos; ++i)
				temp = temp->getNext();
			temp->insert(*newnode);
			head.setValue(head.getValue() + 1);
		}
		///member function to remove an element in linked list
		void remove(int pos) {
			if(pos >= head.getValue() || pos < 0)
				throw invalid_argument("argument out of range");
			NODE *temp = head.getNext();
			for(int i = 0; i < pos; ++i)
				temp = temp->getNext();
			temp->remove();
			head.setValue(head.getValue() - 1);
		}
};
int main() {
	int c, n, p;
	LINKEDLIST list;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> create a new list" << endl
		<< "<2> insert element into list" << endl
		<< "<3> delete element from list" << endl
		<< "<4> print list" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1:	list = LINKEDLIST{}; break;
				case 2: cout << "enter new element and position: ";
						if(!(cin >> n >> p))  throw invalid_argument("arguments of invalid type");
						list.insert(n, p); break;
				case 3: cout << "enter element position: ";
						if(!(cin >> p))  throw invalid_argument("arguments of invalid type");
						list.remove(p); break;
				case 4:	list.printList(); break;
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
