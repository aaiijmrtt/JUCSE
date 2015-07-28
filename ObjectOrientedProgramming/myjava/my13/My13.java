package myjava.my13;

public class My13 {
	public static void main(String args[]) {
		Call obj = new Call();
		try {
			System.out.println("Synchronized:");
			Synchronized obs1 = new Synchronized(obj, "Calling");
			Synchronized obs2 = new Synchronized(obj, "Methods");
			obs1.t.join();
			obs2.t.join();
			System.out.println("\nUnsynchronized:");
			Unsynchronized obu1 = new Unsynchronized(obj, "Calling");
			Unsynchronized obu2 = new Unsynchronized(obj, "Methods");
			obu1.t.join();
			obu2.t.join();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
