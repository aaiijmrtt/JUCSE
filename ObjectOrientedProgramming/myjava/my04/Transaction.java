package myjava.my04;
///class to store and manipulate Transaction values
public class Transaction extends Balance {
	///constructor to initialize data members and validate class invariants
	public Transaction(double bal) {
		super(bal);
	}
	///member function to withdraw amount
	public void withdraw(double amount) throws Exception {
		if(balance < amount)
			throw new Exception("insufficient balance");
		balance -= amount;
		update();
	}
	///member function to deposit amount
	public void deposit(double amount) {
		balance += amount;
		update();
	}
}
