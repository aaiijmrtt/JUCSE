#include <iostream>
#include <string>
#include <cstdlib>
#include <stdexcept>
#include <limits>
#include <ctime>
using namespace std;
///class to store and manipulate ACCOUNT values
class ACCOUNT {
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
		ACCOUNT(double bal=0) : balance{bal} {
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
		virtual bool withdraw(double) = 0;
		virtual void deposit(double) = 0;
};
///class to store and manipulate SAVINGS ACCOUNT values
class SAVINGS: public ACCOUNT {
	public:
		///constructor to initialize data members and validate class invariants
		SAVINGS(double bal=0) : ACCOUNT(bal) {
		}
		///member function to withdraw amount
		bool withdraw(double amount) {
			if(balance < amount + 500.0)
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
///class to store and manipulate CURRENT ACCOUNT values
class CURRENT: public ACCOUNT {
	public:
		///constructor to initialize data members and validate class invariants
		CURRENT(double bal=0) : ACCOUNT(bal) {
		}
		///member function to withdraw amount
		bool withdraw(double amount) {
			if(balance + 20000 < amount)
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
///helper function to open savings account
SAVINGS* openSavingsAccount() {
	double amount;
	cout << "enter opening deposit: ";
	if(!(cin >> amount))
		throw invalid_argument("argument of invalid type");
	return new SAVINGS{amount};
}
///helper function to open current account
CURRENT* openCurrentAccount() {
	double amount;
	cout << "enter opening deposit: ";
	if(!(cin >> amount))
		throw invalid_argument("argument of invalid type");
	return new CURRENT{amount};
}
///helper function to view account details
void viewDetails(ACCOUNT& account) {
	cout << "account: " << account.getAccount() << endl
		<< "last update: " << account.getUpdate()
		<< "balance: " ;
	if(account.getBalance() < 0)
		cout << "overdraft: " << -account.getBalance() << endl;
	else
		cout << account.getBalance() << endl;
}
///helper function to make withdrawal
void withdrawAmount(ACCOUNT& transaction) {
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
void depositAmount(ACCOUNT& transaction) {
	double amount;
	cout << "enter amount to be deposited: ";
	if(!(cin >> amount))
		throw invalid_argument("argument of invalid type");
	transaction.deposit(amount);
}
int main() {
	int c;
	double amount;
	ACCOUNT *account;
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
				case 1: cout << "<1> savings account" << endl
							<< "<2> current account" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						switch(c) {
							case 1: account = openSavingsAccount(); break;
							case 2: account = openCurrentAccount(); break;
							default: cout << "invalid operation code" << endl;
						}
				case 2:	viewDetails(*account); break;
				case 3: withdrawAmount(*account); break;
				case 4: depositAmount(*account); break;
				default: cout << "invalid operation code" << endl;
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
