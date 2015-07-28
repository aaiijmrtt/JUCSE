package myjava.my06;
import java.io.*;
import java.util.*;
///class to store and manipulate Quote values
public class Quote {
	private String name; ///<member to hold file name
	private int count; ///<member to hold count
	private Random random; ///<member to generate random numbers
	///constructor to initialize data members and validate class invariants
	public Quote(String nm) throws IOException, FileNotFoundException {
		name = nm;
		BufferedReader br = new BufferedReader(new FileReader(nm));
		String line;
		random = new Random();
		count = 0;
		while((line = br.readLine()) != null)
			if(line.isEmpty())
				count++;
		br.close();
		random = new Random();
	}
	///getter function for name
	public String getName() {
		return name;
	}
	///getter function for count
	public int getCount() {
		return count;
	}
	///member function to read the line whose number is indicated from file
	public String read(int number) throws IOException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(name));
		String line = null;
		int i = 1;
		while(((line = br.readLine()) != null) && (i < number))
			if(line.isEmpty())
				i++;
		if(line == null) {
			br.close();
			return line;
		}
		String lines = line;
		while(((line = br.readLine()) != null) && (!line.isEmpty()))
			lines += "\n" + line;
		br.close();
		return lines;
	}
	///member function to read a random line from file
	public String readRandom() throws IOException, FileNotFoundException {
		return read(random.nextInt(count));
	}
}
