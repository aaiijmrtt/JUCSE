package myjava.my03;
import myjava.my03.Item;
import myjava.my03.ItemList;
import java.io.*;

public class My03 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ItemList list;
	///enum to check input requirements of item
	private enum Check{in, out, find, revise};
	///helper function to input an item
	Item inputItem(Check check) throws IOException {
		String nm = "";
		int cd, ct = 0;
		double pr = 0.0;
		System.out.print("input code: ");
		cd = Integer.parseInt(br.readLine());
		if((check == Check.in) || (check == Check.out)) {
			System.out.print("input count: ");
			ct = Integer.parseInt(br.readLine());
		}
		if(check == Check.in) {
			System.out.print("input name: ");
			nm = br.readLine();
		}
		if((check == Check.in)	|| (check == Check.revise)) {
			System.out.print("input price: ");
			pr = Double.parseDouble(br.readLine());
		}
		return new Item(nm, cd, ct, pr);
	}
	///helper function to output an item
	void outputItem(Item i) {
		System.out.println("name: " + i.getName() + "\ncode:" + i.getCode() + "\ncount: " + i.getCount() + "\nprice: " + i.getPrice());
	}
	///friend function to output a list
	void outputList(ItemList list) {
		for(int i = 0; i < list.getLast(); ++i) {
			outputItem(list.getList()[i]);
			System.out.println();
		}
	}
	///helper function to add an item to list
	void addItem(ItemList list) throws Exception {
		list.setItem(inputItem(Check.in));
	}
	///helper function to remove an item from list
	void removeItem(ItemList list) throws Exception {
		list.getItem(inputItem(Check.out));
	}
	///helper function to check if an item is in list
	int checkItem(ItemList list) throws IOException {
		return list.findItem(inputItem(Check.find).getCode());
	}
	///friend function to change rate of an item in list
	void changeRate(ItemList list) throws Exception {
		Item item = inputItem(Check.revise);
		int index = list.findItem(item.getCode());
		if(index == -1)
			throw new Exception("unavailable item");
		else
			list.getList()[index].setPrice(item.getPrice());
	}
	public static void main(String[] args) {
		int c = 1;
		My03 obj = new My03();
		System.out.println("\nMENU:\n" + "\n<0> exit" + "\n<1> create a new list" + "\n<2> add an item to list" + "\n<3> remove an item from list" + "\n<4> check an item in list" + "\n<5> change rate of an item on list" + "\n<6> print items on list");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: int n; System.out.print("enter list size: ");
							n = Integer.parseInt(obj.br.readLine());
							obj.list = new ItemList(n); break;
					case 2:	obj.addItem(obj.list); break;
					case 3: obj.removeItem(obj.list); break;
					case 4: if(obj.checkItem(obj.list) == -1) System.out.println("item not found");
							else System.out.println("item found"); break;
					case 5: obj.changeRate(obj.list); break;
					case 6: obj.outputList(obj.list); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
