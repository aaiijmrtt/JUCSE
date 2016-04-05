import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReaderWriter {
	private int readers = 0;
	private int writers = 0;
	private final Lock readlock = new ReentrantLock();
	private final Lock writelock = new ReentrantLock();

	public synchronized void readP() {
		System.out.println("[reader waiting (" + readers + ", " + writers + ")] " + Thread.currentThread().getName());
		while(writers != 0)
			try {
				readlock.wait();
			}
			catch(Exception e) {
			}
		readers++;
	}

	public synchronized void readV() {
		readers--;
		try {
			if(writers > 0)
				writelock.notify();
			else if(writers == 0)
				readlock.notify();
		}
		catch(Exception e) {
		}
	}

	public void read() {
		readP();
		System.out.println("[reader reading (" + readers + ", " + writers + ")] " + Thread.currentThread().getName());
		readV();
	}

	public synchronized void writeP() {
		System.out.println("[writer waiting (" + readers + ", " + writers + ")] " + Thread.currentThread().getName());
		while(writers != 0)
			try {
				writelock.wait();
			}
			catch(Exception e) {
			}
		writers++;
	}

	public synchronized void writeV() {
		writers--;
		try {
			if(writers > 0)
				writelock.notify();
			else if(writers == 0)
				readlock.notify();
		}
		catch(Exception e) {
		}
	}

	public synchronized void write() {
		writeP();
		System.out.println("[writer writing (" + readers + ", " + writers + ")] " + Thread.currentThread().getName());
		writeV();
	}

	public static void main(String args[]) {
		final ReaderWriter rw = new ReaderWriter();
		for(int i = 0; i < Integer.parseInt(args[1]); ++i)
			if(Math.random() < Double.parseDouble(args[0]))
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println("[reader created] " + Thread.currentThread().getName());
							rw.read();
							System.out.println("[reader terminated] " + Thread.currentThread().getName());
						}
						catch(Exception e) {
						}
					}
				}).start();
			else
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println("[writer created] " + Thread.currentThread().getName());
							rw.write();
							System.out.println("[writer terminated] " + Thread.currentThread().getName());
						}
						catch(Exception e) {
						}
					}
				}).start();
	}
}
