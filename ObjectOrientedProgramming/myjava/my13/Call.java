package myjava.my13;
///class to store and manipulate Call values
public class Call {
	///member function to display unsynchronized calls
	public void callunsync(String msg) throws InterruptedException {
		System.out.print("[" + msg);
		Thread.sleep(1000);
		System.out.println("]");
	}
	///member function to display synchronized calls
	public synchronized void callsync(String msg) throws InterruptedException {
		System.out.print("[" + msg);
		Thread.sleep(1000);
		System.out.println("]");
	}
}
