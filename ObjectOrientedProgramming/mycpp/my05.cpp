#include <iostream>
#include <stdexcept>
#include <limits>
#include <string>
using namespace std;
///enum to check input requirements of item
enum CHECK{in, out, find, revise};
///class to store and manipulate ITEM values
class ITEM {
	string name; ///<member to hold name of item
	int code, ///<member to hold code of item
		count; ///<member to hold count of item
	double price; ///<member to hold price of item
	public:
		///constructor to initialize data members and validate class invariants
		ITEM(string nm=string{}, int cd=0, int ct=0, double pr=0.0) : name{nm}, code{cd}, count{ct}, price{pr} {
		}
		///getter function for name
		string getName() {
			return name;
		}
		///getter function for code
		int getCode() {
			return code;
		}
		///getter function for count
		int getCount() {
			return count;
		}
		///getter function for price
		double getPrice() {
			return price;
		}
		///setter function for price
		void setPrice(double pr) {
			price = pr;
		}
		///operator overload increases item count
		ITEM& operator+=(int n) {
			count += n;
			return *this;
		}
		///operator overload decreases item count
		ITEM& operator-=(int n) {
			count -= n;
			return *this;
		}
};
///class to store and manipulate ITEMLIST values
class ITEMLIST {
	int last, ///<member to hold number of items in list
		size; ///<member to hold maximum number of items in list
	ITEM* list; ///<member to hold items in list
	public:
		///constructor to initialize data members and validate class invariants
		ITEMLIST(int n=10) : last{0}, size{n}, list{new ITEM[n]} {
		}
		///setter function to add item to list
		void setItem(ITEM item) {
			for(int i = 0; i < last; ++i)
				if(list[i].getCode() == item.getCode()) {
					list[i] += item.getCount();
					return;
				}
			if(last < size)
				list[last++] = item;
			else
				throw invalid_argument("list overflow");
		}
		///getter function to remove item from list
		void getItem(ITEM item) {
			for(int i = 0; i < last; ++i)
				if(list[i].getCode() == item.getCode()) {
					if(item.getCount() < list[i].getCount()) {
						list[i] -= item.getCount();
						return;
					}
					else if(item.getCount() == list[i].getCount()) {
						--last;
						for(int j = i; j < last; ++j)
							list[j] = list[j + 1];
						return;
					}
					else
						throw invalid_argument("insufficient quantity");
				}
			throw invalid_argument("unavailable item");
		}
		///member function to find item in list
		int findItem(int code) {
			for(int i = 0; i < last; ++i)
				if(code == list[i].getCode())
					return i;
			return -1;
		}
		friend void changeRate(ITEMLIST&);
		friend void outputList(ITEMLIST&);
};
///helper function to input an item
ITEM inputItem(CHECK check) {
	string nm;
	int cd, ct;
	double pr;
	cout << "input code: ";
	cin >> cd;
	if(!cin)
		throw invalid_argument("arguments of invalid type");
	if((check == CHECK::in) || (check == CHECK::out)) {
		cout << "input count: ";
		cin >> ct;
		if(!cin)
			throw invalid_argument("arguments of invalid type");
	}
	if(check == CHECK::in) {
		cout << "input name: ";
		cin >> nm;
		if(!cin)
			throw invalid_argument("arguments of invalid type");
	}
	if((check == CHECK::in)	|| (check == CHECK::revise)) {
		cout << "input price: ";
		cin >> pr;
		if(!cin)
			throw invalid_argument("arguments of invalid type");
	}
	return ITEM{nm, cd, ct, pr};
}
///helper function to output an item
void outputItem(ITEM& i) {
	cout << "name: " << i.getName() << endl << "code:" << i.getCode() << endl << "count: " << i.getCount() << endl << "price: " << i.getPrice() << endl;
}
///friend function to output a list
void outputList(ITEMLIST& list) {
	for(int i = 0; i < list.last; ++i) {
		outputItem(list.list[i]);
		cout << endl;
	}
}
///helper function to add an item to list
void addItem(ITEMLIST& list) {
	list.setItem(inputItem(CHECK::in));
}
///helper function to remove an item from list
void removeItem(ITEMLIST& list) {
	list.getItem(inputItem(CHECK::out));
}
///helper function to check if an item is in list
int checkItem(ITEMLIST& list) {
	return list.findItem(inputItem(CHECK::find).getCode());
}
///friend function to change rate of an item in list
void changeRate(ITEMLIST& list) {
	ITEM item = inputItem(CHECK::revise);
	int index = list.findItem(item.getCode());
	if(index == -1)
		throw invalid_argument("unavailable item");
	else
		list.list[index].setPrice(item.getPrice());
}
int main() {
	int c;
	ITEMLIST list;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> create a new list" << endl
		<< "<2> add an item to list" << endl
		<< "<3> remove an item from list" << endl
		<< "<4> check an item in list" << endl
		<< "<5> change rate of an item on list" << endl
		<< "<6> print items on list" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1: int n; cout << "enter list size: ";
						if(!(cin >> n))  throw invalid_argument("arguments of invalid type");
						list = ITEMLIST{n}; break;
				case 2:	addItem(list); break;
				case 3: removeItem(list); break;
				case 4: if(checkItem(list) == -1) cout << "item not found" << endl;
						else cout << "item found" << endl; break;
				case 5: changeRate(list); break;
				case 6: outputList(list); break;
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
