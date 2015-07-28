package myjava.my07;
import java.io.*;

public class My07 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private MultiQuote multiQuote;
	public static void main(String args[]) {
		int c = 1;
		My07 obj = new My07();
		System.out.println("\nMENU:\n\n<0> exit\n<1> initialize quotes\n<2> generate quote");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: obj.multiQuote = new MultiQuote("./myjava/my06/Quote.txt"); break;
					case 2: System.out.println(obj.multiQuote.readRandom()); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
