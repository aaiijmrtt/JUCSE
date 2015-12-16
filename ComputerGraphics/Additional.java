import java.awt.Point;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Additional {

	//animates a clock with the current system time
	public void clock(int hourshand, int minuteshand, int secondshand, Grid grid) {
		Matrix second = new Matrix(1, 3), minute = new Matrix(1, 3), hour = new Matrix(1, 3), point;
		second.values[0][1] = secondshand;
		second.values[0][2] = 1.0;
		minute.values[0][1] = minuteshand;
		minute.values[0][2] = 1.0;
		hour.values[0][1] = hourshand;
		hour.values[0][2] = 1.0;

		Algorithms algorithm = new Algorithms();
		Transforms transforms = new Transforms();

		int hours, minutes, seconds;
		int max = (int) (1.2 * Math.max(Math.max(secondshand, minuteshand), hourshand));

		while(true) {
			grid.clear();

			seconds = LocalDateTime.now().getSecond();
			point = transforms.transform(transforms.rotation(-(seconds * 3.0) * Math.PI / 90.0), second);
			grid.addExtraLine(0, 0, (int)point.values[0][0], (int)point.values[0][1]);

			minutes = LocalDateTime.now().getMinute();
			point = transforms.transform(transforms.rotation(-(minutes * 3.0) * Math.PI / 90.0), minute);
			grid.addExtraLine(0, 0, (int)point.values[0][0], (int)point.values[0][1]);

			hours = LocalDateTime.now().getHour();
			point = transforms.transform(transforms.rotation(-(hours * 15.0) * Math.PI / 90.0), hour);
			grid.addExtraLine(0, 0, (int)point.values[0][0], (int)point.values[0][1]);

			grid.addExtraOval(-max, max, 2 * max, 2 * max);

			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	//animates a ball travelling along the piecewise linear approximation of a sine curve
	public void sine(int xaxis, int yaxis, int interval, Grid grid) {
		while(true)
			for(int x = 0; x < 360; x+= interval) {
				grid.clear();
				grid.addExtraOval((int)(x / 360.0 * xaxis - xaxis / 2) - 1, (int)(Math.sin(x * Math.PI / 180.0) * yaxis / 2) + 1, 2, 2);
				for(int y = 0; y < 360; y += interval)
					grid.addExtraLine((int)(y / 360.0 * xaxis - xaxis / 2), (int)(Math.sin(y * Math.PI / 180.0) * yaxis / 2), (int)((y + interval) / 360.0 * xaxis - xaxis / 2), (int)(Math.sin((y + interval) * Math.PI / 180.0) * yaxis / 2));

				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
	}

	public static void main(String args[]) throws IOException {
		Grid grid = new Grid(800, 600, 10, 400, 300, false, false);
		System.out.println("Additional Assignments: ");
		System.out.println("1. Clock");
		System.out.println("2. Sine Curve");
		switch(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())) {
			case 1:
				new Display(grid, "Clock");
				new Additional().clock(15, 17, 20, grid);
			break;
			case 2:
				new Display(grid, "Sine");
				new Additional().sine(50, 20, 10, grid);
			break;
			default: System.out.println("Invalid input");
		}
	}

}
