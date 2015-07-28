package myjava.my04;
import java.io.*;

public class My04 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Transaction transaction;
	///helper function to open account
	public Transaction openAccount() throws IOException {
		double amount;
		System.out.print("enter opening deposit: ");
		amount = Double.parseDouble(br.readLine());
		return new Transaction(amount);
	}
	///helper function to view account details
	public void viewDetails(Balance balance) {
		System.out.println("account: " + balance.getAccount() + "\nlast update: " + balance.getUpdate()+ "\nbalance: " + balance.getBalance());
	}
	///helper function to make withdrawal
	public void withdrawAmount(Transaction transaction) throws Exception {
		double amount;
		System.out.print("enter amount to be withdrawn: ");
		amount = Double.parseDouble(br.readLine());
		transaction.withdraw(amount);
		System.out.println("amount successfully withdrawn");
	}
	///helper function to make deposit
	public void depositAmount(Transaction transaction) throws IOException {
		double amount;
		System.out.print("enter amount to be deposited: ");
		amount = Double.parseDouble(br.readLine());
		transaction.deposit(amount);
	}
	public static void main(String args[]) {
		int c = 1;
		double amount;
		My04 obj = new My04();
		System.out.println("\nMENU:\n\n<0> exit\n<1> create a new account\n<2> view account details\n<3> make withdrawal\n<4> make deposit");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: obj.transaction = obj.openAccount(); break;
					case 2:	obj.viewDetails(obj.transaction); break;
					case 3: obj.withdrawAmount(obj.transaction); break;
					case 4: obj.depositAmount(obj.transaction); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
