package myjava.my13;
///class to store and manipulate Synchronized values
public class Synchronized implements Runnable {
	private String msg;
	private Call call;
	public Thread t;
	///constructor to initialize data members and validate class invariants
	public Synchronized(Call targ, String s) {
		call = targ;
		msg = s;
		t = new Thread(this);
		t.start();
	}
	///member function to implement Runnable interface
	public void run() {
		try {
			call.callsync(msg);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
