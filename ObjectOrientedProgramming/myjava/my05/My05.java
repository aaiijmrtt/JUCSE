package myjava.my05;
import java.io.*;
import java.util.*;

public class My05 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	///function to match parenthesis in string
    public boolean check(String str) {
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0 ; i < str.length(); ++i)
			switch(str.charAt(i)) {
				case '(': stack.push(str.charAt(i)); break;
				case '{': stack.push(str.charAt(i)); break;
				case '[': stack.push(str.charAt(i)); break;
				case ')': if((stack.empty()) || (stack.peek() != '(')) return false;
							stack.pop(); break;
				case '}': if((stack.empty()) || (stack.peek() != '{')) return false;
							stack.pop(); break;
				case ']': if((stack.empty()) || (stack.peek() != '[')) return false;
							stack.pop(); break;
			}
		return stack.empty();
	}
	public static void main(String args[]) {
		int c = 1;
		My05 obj = new My05();
		System.out.println("\nMENU:\n\n<0> exit\n<1> check parenthesis");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: System.out.print("enter string to be matched: ");
							if(obj.check(obj.br.readLine())) System.out.println("matched");
							else System.out.println("unmatched"); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
