package myjava.my16;

public class My16 {
	public static void main(String args[]) {
		try {
			new BinaryTree(Integer.parseInt(args[0]));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
