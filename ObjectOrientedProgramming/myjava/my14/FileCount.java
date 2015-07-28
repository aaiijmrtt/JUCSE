package myjava.my14;
import java.io.*;
///class to store and manipulate FileCount values
public class FileCount implements Runnable {
	private File file; ///<member to hold file
	public Thread thread; ///<member to hold thread
	///constructor to initialize data members and validate class invariants
	public FileCount(String nm) {
		file = new File(nm);
		thread = new Thread(this);
		thread.start();
	}
	///member function to count the number of lines in file
	public int count() throws IOException, FileNotFoundException {
		LineNumberReader lnr = new LineNumberReader(new FileReader(file));
	    while((lnr.readLine()) != null);
		int count = lnr.getLineNumber();
		lnr.close();
		return count;
	}
	///member function to implement Runnable
	public void run() {
		try {
			int count = count();
			System.out.println(file.getName() + " has " + count + " lines.");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
