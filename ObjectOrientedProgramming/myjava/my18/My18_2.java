package myjava.my18;
import java.io.*;
import java.util.zip.*;
 
public class My18_2 {
	///helper function to zip file
	public static void gzip(String input) throws IOException {
		String output = input + ".gz";
		byte[] buffer = new byte[1024];
		GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(output));
		FileInputStream in = new FileInputStream(input);
		int len;
		while((len = in.read(buffer)) > 0)
			gzos.write(buffer, 0, len);
		in.close();
		gzos.finish();
		gzos.close();
	}
	public static void main(String args[]) {
		try {
			gzip(args[0]);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
