#include <iostream>
#include <stdexcept>
#include <limits>
using namespace std;
///class to store and manipulate ARRAY values
class ARRAY {
	int *arr, ///<member to hold array of integers
		size; ///<member to hold size of array
	public:
		///constructor to initialize data members and validate class invariants
		ARRAY(int len=1, int init=0) : arr{new int[len]}, size{len} {
			for(int i = 0; i < size; i++)
				arr[i] = init;
		}
		///copy constructor to initialize data members and validate class invariants
		ARRAY(const ARRAY &a) : arr{new int[a.size]}, size{a.size} {
			for(int i = 0; i < size; i++)
				arr[i] = a.arr[i];
		}
		///constructor to initialize data members and validate class invariants from raw array
		ARRAY(int *a, int l) : arr{new int[l]}, size{l} {
			for(int i = 0; i < size; i++)
				arr[i] = a[i];
		}
		///member operator overload to add an array
		ARRAY& operator+=(ARRAY& a) {
			if(a.size > size) {
				for(int i = 0; i < size; i++)
					a.arr[i] += arr[i];
				return a;
			}
			else {
				for(int i = 0; i < a.size; i++)
					arr[i] += a.arr[i];
				return *this;
			}
		}
		///member operator overload to subtract an array
		ARRAY& operator-=(ARRAY& a) {
			if(a.size > size) {
				for(int i = 0; i < size; i++)
					a.arr[i] -= arr[i];
				return a;
			}
			else {
				for(int i = 0; i < a.size; i++)
					arr[i] -= a.arr[i];
				return *this;
			}
		}
		///member operator overload to add by a constant
		ARRAY& operator+=(int n) {
			for(int i = 0; i < size; i++)
				arr[i] += n;
			return *this;
		}
		///member operator overload to subtract by a constant
		ARRAY& operator-=(int n) {
			for(int i = 0; i < size; i++)
				arr[i] -= n;
			return *this;
		}
		///member operator overload to multiply by a constant
		ARRAY& operator*=(int n) {
			for(int i = 0; i < size; i++)
				arr[i] *= n;
			return *this;
		}
		///member operator overload to divide by a constant
		ARRAY& operator/=(int n) {
			for(int i = 0; i < size; i++)
				arr[i] /= n;
			return *this;
		}
		///member operator overload to assign an array
		ARRAY& operator=(const ARRAY& a) {
			size = a.size;
			arr = new int[a.size];
			for(int i = 0; i < size; i++)
				arr[i] = a.arr[i];
			return *this;
		}
		///member operator overload to access by index
		int operator[](int i) {
			if((i < 1) || (i > size))
				throw invalid_argument("argument out of range");
			return arr[i-1];
		}
		///destructor to free memory allocated on freestore
		~ARRAY() {
			free(arr);
		}
		friend ostream& operator<<(ostream&, ARRAY);
};
///operator overload to add two arrays
ARRAY operator+(ARRAY a, ARRAY b) {
	return a += b;
}
///operator overload to subtract two arrays
ARRAY operator-(ARRAY a, ARRAY b) {
	return a -= b;
}
///operator overload to preadd an array by a constant
ARRAY operator+(int n, ARRAY a) {
	return a += n;
}
///operator overload to postadd an array by a constant
ARRAY operator+(ARRAY a, int n) {
	return a += n;
}
///operator overload to subtract an array by a constant
ARRAY operator-(ARRAY a, int n) {
	return a -= n;
}
///operator overload to premultiply an array by a constant
ARRAY operator*(int n, ARRAY a) {
	return a *= n;
}
///operator overload to postmultiply an array by a constant
ARRAY operator*(ARRAY a, int n) {
	return a *= n;
}
///operator overload to divide an array by a constant
ARRAY operator/(ARRAY a, int n) {
	return a /= n;
}
///friend operator overload to output an array
ostream& operator<<(ostream& out, ARRAY a) {
	for(int i = 0; i < a.size; ++i)
		out << a.arr[i] << " ";
	cout << endl;
	return out;
}
int main() {
	int c, *arr = new int[1]{0}, len, length = 1, temp;
	ARRAY array1{}, array2{};
	do {
		cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
			<< "<1> creation" << endl
			<< "<2> assignment" << endl
			<< "<3> array operations" << endl
			<< "<4> constant operations" << endl
			<< "<5> print" << endl
			<< endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1: cout << "<1> create an array" << endl
							<< "<2> create a raw array" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: cout << "enter length of array and constant of initialization: ";
									if(!(cin >> len >> temp))
										throw invalid_argument("arguments of invalid type");
									array1 = ARRAY{len, temp};
									break;
							case 2: cout << "enter length of raw array: ";
									if(!(cin >> length))
										throw invalid_argument("arguments of invalid type");
									arr = new int[length];
									cout << "enter array elements: ";
									for(int i = 0; i < length; ++i) {
										if(!(cin >> temp))
											throw invalid_argument("arguments of invalid type");
										arr[i] = temp;
									}
									break;
						}
						break;
				case 2: cout << "<1> assign a raw array" << endl
							<< "<2> assign another array" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: array1 = ARRAY(arr, length); break;
							case 2: array2 = array1; break;
						}
						break;
				case 3: cout << "<1> preadd the other array" << endl
							<< "<2> postadd the other array" << endl
							<< "<3> presubtract the other array" << endl
							<< "<4> postsubtract the other array" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: array1 = array2 + array1; break;
							case 2: array1 = array1 + array2; break;
							case 3: array1 = array1 - array2; break;
							case 4: array1 = array2 - array1; break;
						}
						break;
				case 4: cout << "enter a constant: ";
						if(!(cin >> temp))
							throw invalid_argument("arguments of invalid type");
						cout << "<1> preadd" << endl
							<< "<2> postadd" << endl
							<< "<3> postsubtract" << endl
							<< "<4> premultiply" << endl
							<< "<5> postmultiply" << endl
							<< "<6> postdivide" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: array1 = temp + array1; break;
							case 2: array1 = array1 + temp; break;
							case 3: array1 = array1 - temp; break;
							case 4: array1 = temp * array1; break;
							case 5: array1 = array1 * temp; break;
							case 6: array1 = array1 / temp; break;
						}
						break;
				case 5: cout << "<1> print array" << endl
							<< "<2> print other array" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: cout << array1; break;
							case 2: cout << array2; break;
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
