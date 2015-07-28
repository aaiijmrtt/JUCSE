#include <iostream>
#include <string>
#include <cstdlib>
#include <stdexcept>
#include <limits>
#include <ctime>
using namespace std;
///class to store and manipulate BALANCE values
class BALANCE {
	int account; ///<member to hold account number
	string updateTime; ///<member to hold time of updation
	protected:
		double balance; ///<member to hold account balance
		///member function to update
		void update() {
			time_t t = time(0);
			updateTime = ctime(&t);
		}
	public:
		///constructor to initialize data members and validate class invariants
		BALANCE(double bal=0) : balance{bal} {
			time_t t = time(0);
			updateTime = ctime(&t);
			srand(t);
			account = 123456789 + rand(); 
		}
		///getter function for account
		int getAccount() {
			return account;
		}
		///getter function for update
		string getUpdate() {
			return updateTime;
		}
		///getter function for balance
		double getBalance() {
			return balance;
		}
};
///class to store and manipulate TRANSACTION values
class TRANSACTION: public BALANCE {
	public:
		///constructor to initialize data members and validate class invariants
		TRANSACTION(double bal=0) : BALANCE(bal) {
		}
		///member function to withdraw amount
		bool withdraw(double amount) {
			if(balance < amount)
				return false;
			balance -= amount;
			update();
			return true;
		}
		///member function to deposit amount
		void deposit(double amount) {
			balance += amount;
			update();
		}
};
///helper function to open account
TRANSACTION openAccount() {
	double amount;
	cout << "enter opening deposit: ";
	if(!(cin >> amount))
		throw invalid_argument("argument of invalid type");
	return TRANSACTION{amount};
}
///helper function to view account details
void viewDetails(BALANCE& balance) {
	cout << "account: " << balance.getAccount() << endl
		<< "last update: " << balance.getUpdate()
		<< "balance: " << balance.getBalance() << endl;
}
///helper function to make withdrawal
void withdrawAmount(TRANSACTION& transaction) {
	double amount;
	cout << "enter amount to be withdrawn: ";
	if(!(cin >> amount))
		throw invalid_argument("argument of invalid type");
	if(transaction.withdraw(amount))
		cout << "amount successfully withdrawn" << endl;
	else
		cout << "insufficient balance" << endl;
}
///helper function to make deposit
void depositAmount(TRANSACTION& transaction) {
	double amount;
	cout << "enter amount to be deposited: ";
	if(!(cin >> amount))
		throw invalid_argument("argument of invalid type");
	transaction.deposit(amount);
}
int main() {
	int c;
	double amount;
	TRANSACTION transaction;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> create a new account" << endl
		<< "<2> view account details" << endl
		<< "<3> make withdrawal" << endl
		<< "<4> make deposit" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1: transaction = openAccount(); break;
				case 2:	viewDetails(transaction); break;
				case 3: withdrawAmount(transaction); break;
				case 4: depositAmount(transaction); break;
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
