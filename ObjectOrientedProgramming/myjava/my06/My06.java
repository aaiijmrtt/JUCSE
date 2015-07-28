package myjava.my06;

public class My06 {
	private Quote quote;
	public static void main(String args[]) {
		My06 obj = new My06();
		try {
			obj.quote = new Quote("./myjava/my06/Quote.txt");
			System.out.println(obj.quote.readRandom());
		}
		catch(Exception e) {
			System.out.println(e.toString() + ": try again");
		}
	}
}
