package myjava.my03;
///class to store and manipulate Item values
public class Item {
	private String name; ///<member to hold name of item
	private int code, ///<member to hold code of item
		count; ///<member to hold count of item
	private double price; ///<member to hold price of item
	///constructor to initialize data members and validate class invariants
	public Item(String nm, int cd, int ct, double pr) {
		name = nm;
		code = cd;
		count = ct;
		price = pr;
	}
	///getter function for name
	public String getName() {
		return name;
	}
	///getter function for code
	public int getCode() {
		return code;
	}
	///getter function for count
	public int getCount() {
		return count;
	}
	///getter function for price
	public double getPrice() {
		return price;
	}
	///setter function for price
	public void setPrice(double pr) {
		price = pr;
	}
	public Item increment(int inc) {
		count += inc;
		return this;
	}
	public Item decrement(int dec) {
		count -= dec;
		return this;
	}
};
