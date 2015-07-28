package myjava.my07;
import myjava.my06.Quote;
import java.io.*;
import java.util.*;
///class to store and manipulate MultiQuote values
public class MultiQuote extends Quote {
	private int showCount; ///<member to hold show count
	private boolean show[]; ///<member to hold show states
	private Random random; ///<member to generate random numbers
	///constructor to initialize data members and validate class invariants
	public MultiQuote(String nm) throws IOException, FileNotFoundException {
		super(nm);
		random = new Random();
		showCount = 0;
		show = new boolean[getCount()];
	}
	///member function to generate a checked random number
	private int randomize() {
		if(showCount == getCount()) {
			showCount = 0;
			for(int i = 0; i < getCount(); ++i)
				show[i] = false;
		}
		int rand = random.nextInt(getCount() - showCount);
		for(int i = 0, j = 0; i < getCount(); ++i) {
			if(!show[i])
				j++;
			if(j == rand) {
				show[i] = true;
				return i;
			}
		}
		return rand;
	}
	///member function to read a random line from file
	public String readRandom() throws IOException, FileNotFoundException {
		return read(randomize());
	}
}
