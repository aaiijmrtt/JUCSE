package myjava.my04;
import java.util.*;
///class to store and manipulate Balance values
public class Balance {
	private int account; ///<member to hold account number
	private String updateTime; ///<member to hold time of updation
	protected double balance; ///<member to hold account balance
	///member function to update
	protected void update() {
		Date update = new Date();
		updateTime = update.toString();
	}
	///constructor to initialize data members and validate class invariants
	public Balance(double bal) {
		balance = bal;
		Date update = new Date();
		updateTime = update.toString();
		account = (int)(Math.random() * 1000000000); 
	}
	///getter function for account
	public int getAccount() {
		return account;
	}
	///getter function for update
	public String getUpdate() {
		return updateTime;
	}
	///getter function for balance
	public double getBalance() {
		return balance;
	}
}
