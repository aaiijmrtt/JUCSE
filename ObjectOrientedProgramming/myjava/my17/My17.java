package myjava.my17; 
import java.io.*;

public class My17 {
	///helper function to distribute sizes not exceeding max by a greedy algorithm
	public void distribute(int sizes[], int max) {
		java.util.Arrays.sort(sizes);
		boolean check = true, left[] = new boolean[sizes.length];
		int i, size;
		for(i = 0; i < sizes.length; ++i)
			left[i] = true;
		while(check) {
			check = false;
			size = max;
			for(i = sizes.length - 1; i >= 0; --i)
				if(left[i])
					if(sizes[i] <= size) {
						System.out.print(sizes[i] + " ");
						size -= sizes[i];
						left[i] = false;
					}
					else if(sizes[i] <= max)
						check = true;
			System.out.println();
		}
	}
	///helper function to interact with the user, accept input and generate output
	public void interact() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("input file sizes: ");
		String array[] = br.readLine().split(" ");
		int sizes[] = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			sizes[i] = Integer.parseInt(array[i]);
		System.out.print("input directory size: ");
		int max = Integer.parseInt(br.readLine());
		distribute(sizes, max);
	}
	public static void main(String args[]) {
		try {
			new My17().interact();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
