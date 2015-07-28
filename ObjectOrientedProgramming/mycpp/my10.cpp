#include <iostream>
#include <stdexcept>
#include <limits>
using namespace std;
///class to store and manipulate STRING values
class STRING {
	char *str; ///<member to hold array of characters
	int size; ///<member to hold size of array
	public:
		///constructor to initialize data members and validate class invariants
		STRING(int len=1, char init='\0') : str{new char[len]}, size{len} {
			for(int i = 0; i < size; ++i)
				str[i] = init;
		}
		///copy constructor to initialize data members and validate class invariants
		STRING(const STRING &a) : str{new char[a.size]}, size{a.size} {
			for(int i = 0; i < size; i++)
				str[i] = a.str[i];
		}
		///constructor to initialize data members and validate class invariants from raw string
		STRING(char *a, int len) : str{new char[len]}, size{len} {
			for(int i = 0; i < size; i++)
				str[i] = a[i];
		}
		///member operator overload to add a string
		STRING& operator+=(STRING& a) {
			char *ptr = new char[size + a.size];
			for(int i = 0; i < size; i++)
				ptr[i] = str[i];
			for(int i = size; i < size + a.size; i++)
				ptr[i] = a.str[i - size];
			size += a.size;
			str = ptr;
			return *this;
		}
		///member operator overload to assign a string
		STRING& operator=(const STRING& a) {
			size = a.size;
			str = new char[size];
			for(int i = 0; i < size; i++)
				str[i] = a.str[i];
			return *this;
		}
		///member operator overload to check for equality
		bool operator==(const STRING& a) {
			if(size != a.size)
				return false;
			int i;
			for(i = 0; i < size; ++i)
				if(str[i] != a.str[i])
					break;
			if(i < size)
				return false;
			return true;
		}
		///member operator overload to compare
		bool operator<(const STRING& a) {
			int i;
			for(i = 0; (i < size) && (i < a.size); ++i)
				if(str[i] != a.str[i])
					break;
			if((i < size) && (i < a.size))
				return str[i] < a.str[i];
			return size < a.size;
		}
		///member operator overload to compare
		bool operator>(const STRING& a) {
			int i;
			for(i = 0; (i < size) && (i < a.size); ++i)
				if(str[i] != a.str[i])
					break;
			if((i < size) && (i < a.size))
				return str[i] > a.str[i];
			return size > a.size;
		}
		///member operator overload to access by index
		char operator[](int i) {
			if((i < 1) || (i > size))
				throw invalid_argument("argument out of range");
			return str[i-1];
		}
		///destructor to free memory allocated on freestore
		~STRING() {
			free(str);
		}
		friend ostream& operator<<(ostream&, STRING);
};
///operator overload to check for inequality
bool operator!=(STRING& a, STRING& b) {
	return !(a == b);
}
///operator overload to compare
bool operator<=(STRING& a, STRING& b) {
	return !(a > b);
}
///operator overload to compare
bool operator>=(STRING& a, STRING& b) {
	return !(a < b);
}
///operator overload to add a string
STRING operator+(STRING a, STRING b) {
	return a += b;
}
///friend operator overload to output a string
ostream& operator<<(ostream& out, STRING a) {
	for(int i = 0; i < a.size; ++i)
		out << a.str[i];
	return out;
}
int main() {
	int c, len, length = 1;
	char *str = new char[1]{'\0'}, temp;
	STRING string1{}, string2{};
	do {
		cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
			<< "<1> creation" << endl
			<< "<2> assignment" << endl
			<< "<3> addition operations" << endl
			<< "<4> comparison operations" << endl
			<< "<5> print" << endl
			<< endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1: cout << "<1> create a string" << endl
							<< "<2> create a raw string" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: cout << "enter length of string and constant of initialization: ";
									if(!(cin >> len >> temp))
										throw invalid_argument("arguments of invalid type");
									string1 = STRING{len, temp};
									break;
							case 2: cout << "enter length of raw string: ";
									if(!(cin >> length))
										throw invalid_argument("arguments of invalid type");
									str = new char[length];
									cout << "enter string elements: ";
									for(int i = 0; i < length; ++i) {
										if(!(cin >> temp))
											throw invalid_argument("arguments of invalid type");
										str[i] = temp;
									}
									break;
						}
						break;
				case 2: cout << "<1> assign a raw string" << endl
							<< "<2> assign another string" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: string1 = STRING(str, length); break;
							case 2: string2 = string1; break;
						}
						break;
				case 3: cout << "<1> preadd the other string" << endl
							<< "<2> postadd the other string" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: string1 = string2 + string1; break;
							case 2: string1 = string1 + string2; break;
						}
						break;
				case 4: cout << "check if: " << endl
							<< "<1> equal to" << endl
							<< "<2> less than" << endl
							<< "<3> greater than" << endl
							<< "<4> less than or equal to" << endl
							<< "<5> greater than or equal to" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1:	if(string1 == string2) cout << "yes" << endl; else cout << "no" << endl; break;
							case 2:	if(string1 < string2) cout << "yes" << endl; else cout << "no" << endl; break;
							case 3:	if(string1 > string2) cout << "yes" << endl; else cout << "no" << endl; break;
							case 4:	if(string1 <= string2) cout << "yes" << endl; else cout << "no" << endl; break;
							case 5:	if(string1 >= string2) cout << "yes" << endl; else cout << "no" << endl; break;
						}
						break;
				case 5: cout << "<1> print string" << endl
							<< "<2> print other string" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: cout << string1 << endl; break;
							case 2: cout << string2 << endl; break;
						}
						break;
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
