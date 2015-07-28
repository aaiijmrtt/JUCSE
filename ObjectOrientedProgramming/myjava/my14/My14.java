package myjava.my14;

public class My14 {
	///helper function to count lines in file sequentially
	public void callSequentially(String names[]) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		FileCount filecount;
		for(String name : names) {
			filecount = new FileCount(name);
			filecount.thread.join();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("sequential counting time: " + (endTime - startTime) + "ms");
	}
	///helper function to count lines in file parallelly
	public void callParallelly(String names[]) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		FileCount filecount[] = new FileCount[names.length];
		int i = 0;
		for(String name : names)
			filecount[i++] = new FileCount(name);
		for(FileCount count : filecount)
			count.thread.join();
		long endTime = System.currentTimeMillis();
		System.out.println("parallel counting time: " + (endTime - startTime) + "ms");
	}
	public static void main(String args[]) {
		My14 obj = new My14();
		try {
			obj.callParallelly(args);
			obj.callSequentially(args);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
