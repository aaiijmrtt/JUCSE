package myjava.my18;
import java.io.*;

public class My18_1 {
	///helper function to run a system command
	public static void run(String command) {
		Runtime rt = null;
		Process proc = null;
		BufferedReader br = null;
		try {
			rt = Runtime.getRuntime();
			proc = rt.exec(command);
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine()) != null)
				System.out.println(line);
			proc.waitFor();
			br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			rt = null;
			proc = null;
			br = null;
		}
	}
	public static void main(String args[]) {
		String command = "ls ";
		if(args.length == 1)
			command += args[0];
		else if((args.length == 2) && (args[1].charAt(0) == '-'))
			switch(args[1].charAt(1)) {
				case 'd': command += "-lt " + args[0]; break;
				case 's': command += "-lS " + args[0]; break;
			}
		run(command);
	}
}
