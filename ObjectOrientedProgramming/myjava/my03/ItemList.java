package myjava.my03;
import myjava.my03.Item;
///class to store and manipulate ItemList values
public class ItemList {
	private int last, ///<member to hold number of items in list
		size; ///<member to hold maximum number of items in list
	private Item list[]; ///<member to hold items in list
	///constructor to initialize data members and validate class invariants
	public ItemList(int n) {
		last = 0;
		size = n;
		list = new Item[n];
	}
	///getter function for last
	public int getLast() {
		return last;
	}
	///getter function for size
	public int getSize() {
		return size;
	}
	///getter function for list
	public Item[] getList() {
		return list;
	}
	///setter function to add item to list
	public void setItem(Item item) throws Exception {
		for(int i = 0; i < last; ++i)
			if(list[i].getCode() == item.getCode()) {
				list[i].increment(item.getCount());
				return;
			}
		if(last < size)
			list[last++] = item;
		else
			throw new Exception("list full");
	}
	///getter function to remove item from list
	public void getItem(Item item) throws Exception {
		for(int i = 0; i < last; ++i)
			if(list[i].getCode() == item.getCode()) {
				if(item.getCount() < list[i].getCount()) {
					list[i].decrement(item.getCount());
					return;
				}
				else if(item.getCount() == list[i].getCount()) {
					--last;
					for(int j = i; j < last; ++j)
						list[j] = list[j + 1];
					return;
				}
				else
					throw new Exception("insufficient quantity");
			}
		throw new Exception("unavailable item");
	}
	///member function to find item in list
	public int findItem(int code) {
		for(int i = 0; i < last; ++i)
			if(code == list[i].getCode())
				return i;
		return -1;
	}
}
