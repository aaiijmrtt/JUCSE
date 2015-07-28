#include <iostream>
#include <stdexcept>
#include <limits>
using namespace std;
///class to store and manipulate TOLLTAX values
class TOLLTAX {
	int *cars, ///<member to hold array of cars tolled
		*toll, ///<member to hold array of toll tax collected
		total; ///<member to hold total toll tax collected
	public:
		static const int maxType = 20; ///<member to hold upper limit of car types
		///constructor to initialize data members and validate class invariants
		TOLLTAX() : cars{new int[maxType]}, toll{new int[maxType]}, total{0} {
			for(int i = 0; i < maxType; ++i) {
				cars[i] = 0;
				toll[i] = 10 * i;
			}
		}
		///setter function for collecting toll
		void setToll(int i) {
			if((i < 0) || (i >= maxType))
				throw invalid_argument("arguments out of range");
			cars[i]++;
			total += toll[i];
		}
		///getter function for collecting toll
		int getToll(int i) {
			if((i < 0) || (i >= maxType))
				throw invalid_argument("arguments out of range");
			return cars[i] * toll[i];
		}
		///getter function for total
		int getTotal() {
			return total;
		}
};
///helper function to input and collect toll
void collectToll(TOLLTAX& taxer) {
	int wheels;
	cout << "input number of wheels: ";
	cin >> wheels;
	if(!cin)
		throw invalid_argument("invalid arguments");
	taxer.setToll(wheels);
}
///helper function to output toll collected for car type
void viewToll(TOLLTAX& taxer) {
	int wheels;
	cout << "input number of wheels: ";
	cin >> wheels;
	if(!cin)
		throw invalid_argument("invalid arguments");
	cout << "toll collected for " << wheels << "wheelers is Rs " << taxer.getToll(wheels) << endl;
}
///helper function to output total toll collected
void viewTotal(TOLLTAX& taxer) {
	cout << "total toll collected is Rs " << taxer.getTotal() << endl;
}
int main() {
	int c;
	TOLLTAX taxer;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> collect toll for a vehicle" << endl
		<< "<2> get toll collected for a vehicle type" << endl
		<< "<3> get total toll collected" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1:	collectToll(taxer); break;
				case 2: viewToll(taxer); break;
				case 3: viewTotal(taxer); break;
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
