package myjava.my09;

public class My09 {
	public static void main(String args[]) {
		try {
			FileIndex fi = new FileIndex(args[0]);
			fi.index();
			fi.report();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
