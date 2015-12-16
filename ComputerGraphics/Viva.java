import java.awt.Point;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Viva {

	public static ArrayList<Point> pointsFromMatrix(Matrix mat) {
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < mat.rows; ++i)
			points.add(new Point((int)mat.values[i][0], (int)mat.values[i][1]));
		return points;
	}

	public static Matrix matrixFromPoints(ArrayList<Point> points) {
		int count = points.size();
		Matrix mat = new Matrix(count, 3);
		for(int i = 0; i < count; ++i) {
			Point point = points.get(i);
			mat.values[i][0] = point.getX();
			mat.values[i][1] = point.getY();
			mat.values[i][2] = 1.0;
		}
		return mat;
	}

	public static int getInt(String prompt) throws IOException {
		System.out.println(prompt);
		return Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
	}

	public static double getDouble(String prompt) throws IOException {
		System.out.println(prompt);
		return Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
	}

	public void Test_Algorithm() throws IOException {
		System.out.println("Algorithms: ");
		System.out.println("1. Line: Digital Differential Analyzer");
		System.out.println("2. Line: Bresenham");
		System.out.println("3. Line: Bresenham Integer");
		System.out.println("4. Line: Bresenham Integer Generalized");
		System.out.println("5. Line: Midpoint");
		System.out.println("6. Line: Midpoint First Order");
		System.out.println("7. Circle: Naive");
		System.out.println("8. Circle: Bresenham");
		System.out.println("9. Circle: Midpoint First Order");
		System.out.println("10. Circle: Midpoint Second Order");
		System.out.println("11. Ellipse: Naive");
		System.out.println("12. Ellipse: Midpoint First Order");
		System.out.println("13. Polygon Filling: Ordered Edge List");
		System.out.println("14. Polygon Filling: Edge Fill");
		System.out.println("15. Polygon Filling: Fence Fill");
		System.out.println("16. Polygon Filling: Flag Fill");
		System.out.println("17. Polygon Filling: Seed Fill");
		System.out.println("18. Polygon Filling: Scan Line Seed Fill");
		System.out.println("19. Line Clipping: Cohen Sutherland");
		System.out.println("20. Line Clipping: Cyrus Beck");

		int choice = Viva.getInt("Choose Algorithm");
		int argument1 = 0, argument2 = 0, argument3 = 0, argument4 = 0, argument5 = 0, argument6 = 0, argument7 = 0, argument8 = 0;
		Algorithms algorithm = new Algorithms();
		ArrayList<Point> test = new ArrayList<Point>();
		Grid grid;

		if(choice > 0 && choice < 21)
			grid = new Grid(800, 600, 10, 400, 300, false);
		else {
			System.out.println("invalid input");
			return;
		}

		switch(choice) {
			case 1: case 2: case 3: case 4: case 5: case 6:
				argument1 = Viva.getInt("Enter point 1, x coordinate");
				argument2 = Viva.getInt("Enter point 1, y coordinate");
				argument3 = Viva.getInt("Enter point 2, x coordinate");
				argument4 = Viva.getInt("Enter point 2, y coordinate");
				grid.addExtraLine(argument1, argument2, argument3, argument4);
			break;
			case 7: case 8: case 9: case 10:
				argument1 = Viva.getInt("Enter radius");
				argument2 = Viva.getInt("Enter origin, x coordinate");
				argument3 = Viva.getInt("Enter origin, y coordinate");
				grid.addExtraOval(argument2 - argument1, argument3 + argument1, 2 * argument1, 2 * argument1);
			break;
			case 11: case 12:
				argument1 = Viva.getInt("Enter semi axis along x axis");
				argument2 = Viva.getInt("Enter semi axis along y axis");
				argument3 = Viva.getInt("Enter origin, x coordinate");
				argument4 = Viva.getInt("Enter origin, y coordinate");
				grid.addExtraOval(argument3 - argument1, argument4 + argument2, 2 * argument1, 2 * argument2);
			break;
			case 17: case 18:
				argument1 = Viva.getInt("Enter seed, x coordinate");
				argument2 = Viva.getInt("Enter seed, y coordinate");
			case 13: case 14: case 15: case 16:
				test.add(new Point(-12, -6));
				test.add(new Point(-12, 12));
				test.add(new Point(0, 0));
				test.add(new Point(9, 9));
				test.add(new Point(9, -6));
			break;
			case 19:
				argument1 = Viva.getInt("Enter clip boundary, x lower");
				argument2 = Viva.getInt("Enter clip boundary, x higher");
				argument3 = Viva.getInt("Enter clip boundary, y lower");
				argument4 = Viva.getInt("Enter clip boundary, y higher");
				argument5 = Viva.getInt("Enter point 1, x coordinate");
				argument6 = Viva.getInt("Enter point 1, y coordinate");
				argument7 = Viva.getInt("Enter point 2, x coordinate");
				argument8 = Viva.getInt("Enter point 2, y coordinate");
				grid.addExtraLine(argument1, argument3, argument1, argument4);
				grid.addExtraLine(argument2, argument3, argument2, argument4);
				grid.addExtraLine(argument1, argument3, argument2, argument3);
				grid.addExtraLine(argument1, argument4, argument2, argument4);
				grid.addExtraLine(argument5, argument6, argument7, argument8);
			break;
			case 20:
				test.add(new Point(Viva.getInt("Enter clip point 1, x coordinate"), Viva.getInt("Enter clip point 1, y coordinate")));
				test.add(new Point(Viva.getInt("Enter clip point 2, x coordinate"), Viva.getInt("Enter clip point 2, y coordinate")));
				test.add(new Point(Viva.getInt("Enter clip point 3, x coordinate"), Viva.getInt("Enter clip point 3, y coordinate")));
				test.add(new Point(Viva.getInt("Enter clip point 4, x coordinate"), Viva.getInt("Enter clip point 4, y coordinate")));
				argument1 = Viva.getInt("Enter point 1, x coordinate");
				argument2 = Viva.getInt("Enter point 1, y coordinate");
				argument3 = Viva.getInt("Enter point 2, x coordinate");
				argument4 = Viva.getInt("Enter point 2, y coordinate");
				for(int i = 0; i < test.size(); ++i)
					grid.addExtraLine((int)test.get(i).getX(), (int)test.get(i).getY(), (int)test.get((i + 1) % test.size()).getX(), (int)test.get((i + 1) % test.size()).getY());
				grid.addExtraLine(argument1, argument2, argument3, argument4);
			break;
		}

		switch(choice) {

			case 1: grid.setPixels(algorithm.Line_DigitalDifferentialAnalyzer(new Point(argument1, argument2), new Point(argument3, argument4))); break; 
			case 2: grid.setPixels(algorithm.Line_Bresenham(new Point(argument1, argument2), new Point(argument3, argument4))); break;
			case 3: grid.setPixels(algorithm.Line_BresenhamInteger(new Point(argument1, argument2), new Point(argument3, argument4))); break;
			case 4: grid.setPixels(algorithm.Line_BresenhamIntegerGeneralized(new Point(argument1, argument2), new Point(argument3, argument4))); break;
			case 5: grid.setPixels(algorithm.Line_Midpoint(new Point(argument1, argument2), new Point(argument3, argument4))); break;
			case 6: grid.setPixels(algorithm.Line_MidpointFirstOrder(new Point(argument1, argument2), new Point(argument3, argument4))); break;

			case 7: grid.setPixels(algorithm.ShiftOrigin(new Point(argument2, argument3), algorithm.Circle_Midpoint(argument1))); break;
			case 8: grid.setPixels(algorithm.ShiftOrigin(new Point(argument2, argument3), algorithm.Circle_Bresenham(argument1))); break;
			case 9: grid.setPixels(algorithm.ShiftOrigin(new Point(argument2, argument3), algorithm.Circle_MidpointFirstOrder(argument1))); break;
			case 10: grid.setPixels(algorithm.ShiftOrigin(new Point(argument2, argument3), algorithm.Circle_MidpointSecondOrder(argument1))); break;

			case 11: grid.setPixels(algorithm.ShiftOrigin(new Point(argument3, argument4), algorithm.Ellipse_Midpoint(argument1, argument2))); break;
			case 12: grid.setPixels(algorithm.ShiftOrigin(new Point(argument3, argument4), algorithm.Ellipse_MidpointFirstOrder(argument1, argument2))); break;

			case 13: grid = new Grid(800, 600, 10, 400, 300, true); grid.setPixels(algorithm.Polygon_OrderedEdgeList(test)); break;
			case 14: grid = new Grid(800, 600, 10, 400, 300, true); grid.setPixels(algorithm.Polygon_EdgeFill(test)); break;
			case 15: grid = new Grid(800, 600, 10, 400, 300, true); grid.setPixels(algorithm.Polygon_FenceFill(test)); break;
			case 16: grid = new Grid(800, 600, 10, 400, 300, true); grid.setPixels(algorithm.Polygon_EdgeFlag(test)); break;
			case 17: grid = new Grid(800, 600, 10, 400, 300, true); grid.setPixels(algorithm.Polygon_SeedFillSimple(test, new Point(argument1, argument2))); break;
			case 18: grid = new Grid(800, 600, 10, 400, 300, true); grid.setPixels(algorithm.Polygon_SeedFillScanLine(test, new Point(argument1, argument2))); break;

			case 19: grid.setPixels(algorithm.Clipping_CohenSutherland(new Point(argument5, argument6), new Point(argument7, argument8), argument1, argument2, argument3, argument4));
			case 20: grid.setPixels(algorithm.Clipping_CyrusBeck(new Point(argument1, argument2), new Point(argument3, argument4), test)); break;

		}

		new Display(grid, "Output");
	}

	public void Test_Transform() throws IOException {
		System.out.println("Transforms: ");
		System.out.println("1. 2D: Scaling");
		System.out.println("2. 2D: Shearing");
		System.out.println("3. 2D: Translation");
		System.out.println("4. 2D: Rotation");
		System.out.println("5. 2D: Reflection about X axis");
		System.out.println("6. 2D: Reflection about Y axis");
		System.out.println("7. 2D: Reflection about Y = X line");
		System.out.println("8. 2D: Reflection about Y = -X line");
		System.out.println("9. 2D: Rotation about a point");
		System.out.println("10. 3D: Scaling");
		System.out.println("11. 3D: Rotation");
		System.out.println("12. 3D: Translation");
		System.out.println("13. 3D: Reflection about XY plane");
		System.out.println("14. 3D: Reflection about YZ plane");
		System.out.println("15. 3D: Reflection about ZX plane");
		System.out.println("16. 3D: Perspective transform from point on X axis");
		System.out.println("17. 3D: Perspective transform from point on Y axis");
		System.out.println("18. 3D: Perspective transform from point on Z axis");

		int choice = Viva.getInt("Choose Transform");
		double argument1 = 0, argument2 = 0, argument3 = 0;
		Transforms transforms = new Transforms();
		Algorithms algorithm = new Algorithms();
		ArrayList<Point> points = new ArrayList<Point>(), transformedpoints = new ArrayList<Point>();
		Grid grid1 = new Grid(800, 600, 10, 400, 300, false), grid2 = new Grid(800, 600, 10, 400, 300, false);
		Matrix pointmatrix;

		if(choice > 0 && choice < 10) {
			points.add(new Point(0, 0));
			points.add(new Point(0, 10));
			points.add(new Point(10, 10));
			points.add(new Point(10, 0));
			pointmatrix = Viva.matrixFromPoints(points);
			for(int i = 0; i < points.size(); ++i)
				grid1.setPixels(algorithm.Line_BresenhamIntegerGeneralized(points.get(i), points.get((i + 1) % points.size())));
		}
		else if(choice > 9 && choice < 19) {
			pointmatrix = new Matrix(8, 4);
			pointmatrix.values[0][0] = 15;
			pointmatrix.values[0][1] = 15;
			pointmatrix.values[0][2] = 15;
			pointmatrix.values[0][3] = 1;
			pointmatrix.values[1][0] = 15;
			pointmatrix.values[1][1] = 15;
			pointmatrix.values[1][2] = 0;
			pointmatrix.values[1][3] = 1;
			pointmatrix.values[2][0] = 15;
			pointmatrix.values[2][1] = 0;
			pointmatrix.values[2][2] = 0;
			pointmatrix.values[2][3] = 1;
			pointmatrix.values[3][0] = 15;
			pointmatrix.values[3][1] = 0;
			pointmatrix.values[3][2] = 15;
			pointmatrix.values[3][3] = 1;
			pointmatrix.values[4][0] = 0;
			pointmatrix.values[4][1] = 0;
			pointmatrix.values[4][2] = 15;
			pointmatrix.values[4][3] = 1;
			pointmatrix.values[5][0] = 0;
			pointmatrix.values[5][1] = 15;
			pointmatrix.values[5][2] = 15;
			pointmatrix.values[5][3] = 1;
			pointmatrix.values[6][0] = 0;
			pointmatrix.values[6][1] = 15;
			pointmatrix.values[6][2] = 0;
			pointmatrix.values[6][3] = 1;
			pointmatrix.values[7][0] = 0;
			pointmatrix.values[7][1] = 0;
			pointmatrix.values[7][2] = 0;
			pointmatrix.values[7][3] = 1;

			transformedpoints = Test.pointsFromMatrix(pointmatrix);
			for(int i = 0; i < transformedpoints.size() - 1; ++i) {
				grid1.addExtraLine((int)transformedpoints.get(i).getX(), (int)transformedpoints.get(i).getY(), (int)transformedpoints.get((i + 1) % transformedpoints.size()).getX(), (int)transformedpoints.get((i + 1) % transformedpoints.size()).getY());
				if(i < transformedpoints.size() / 2) {
					grid1.addExtraLine((int)transformedpoints.get(i).getX(), (int)transformedpoints.get(i).getY(), (int)transformedpoints.get((i + 5) % transformedpoints.size()).getX(), (int)transformedpoints.get((i + 5) % transformedpoints.size()).getY());
				}
				else if(i == transformedpoints.size() / 2) {
					grid1.addExtraLine((int)transformedpoints.get(i).getX(), (int)transformedpoints.get(i).getY(), (int)transformedpoints.get(transformedpoints.size() - 1).getX(), (int)transformedpoints.get(transformedpoints.size() - 1).getY());
				}
			}
		}
		else {
			System.out.println("Invalid Input");
			return;
		}

		new Display(grid1, "Original");

		switch(choice) {
			case 1: case 2: case 3:
				argument1 = Viva.getDouble("Enter X value");
				argument2 = Viva.getDouble("Enter Y value");
			break;
			case 4:
				argument1 = Viva.getDouble("Enter angle of rotation");
			break;
			case 9:
				argument1 = Viva.getDouble("Enter point, x coordinate");
				argument2 = Viva.getDouble("Enter point, y coordinate");
				argument3 = Viva.getDouble("Enter angle of rotation");
			break;
			case 10: case 11: case 12:
				argument1 = Viva.getDouble("Enter X value");
				argument2 = Viva.getDouble("Enter Y value");
				argument3 = Viva.getDouble("Enter Z value");
			break;
			case 16: case 17: case 18:
				argument1 = Viva.getDouble("Enter value");
			break;
		}

		switch(choice) {
			case 1: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.scaling(argument1, argument2), pointmatrix)); break;
			case 2: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.shearing(argument1, argument2), pointmatrix)); break;
			case 3: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.translation(argument1, argument2), pointmatrix)); break;
			case 4: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.rotation(argument1 / 180.0 * Math.PI), pointmatrix)); break;
			case 5: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflection_xaxis(), pointmatrix)); break;
			case 6: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflection_yaxis(), pointmatrix)); break;
			case 7: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflection_yequaltox(), pointmatrix)); break;
			case 8: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflection_yequaltominusx(), pointmatrix)); break;
			case 9: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.rotationaboutpoint(argument1, argument2, argument3 / 180.0 * Math.PI), pointmatrix)); break;

			case 10: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.scaling(argument1, argument2, argument3), pointmatrix)); break;
			case 11: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.rotation(argument1 / 180.0 * Math.PI, argument2 / 180.0 * Math.PI, argument3 / 180.0 * Math.PI), pointmatrix)); break;
			case 12: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.translation(argument1, argument2, argument3), pointmatrix)); break;
			case 13: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflectionXY(), pointmatrix)); break;
			case 14: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflectionYZ(), pointmatrix)); break;
			case 15: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.reflectionZX(), pointmatrix)); break;

			case 16: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.perspectiveX(argument1), pointmatrix)); break;
			case 17: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.perspectiveY(argument1), pointmatrix)); break;
			case 18: transformedpoints = Test.pointsFromMatrix(transforms.transform(transforms.perspectiveZ(argument1), pointmatrix)); break;
		}

		Grid grid3 = new Grid(800, 600, 10, 400, 300, false);

		if(choice < 10)
			for(int i = 0; i < transformedpoints.size(); ++i)
				grid2.setPixels(algorithm.Line_BresenhamIntegerGeneralized(transformedpoints.get(i), transformedpoints.get((i + 1) % transformedpoints.size())));
		else
			for(int i = 0; i < transformedpoints.size() - 1; ++i) {
				grid2.addExtraLine((int)transformedpoints.get(i).getX(), (int)transformedpoints.get(i).getY(), (int)transformedpoints.get((i + 1) % transformedpoints.size()).getX(), (int)transformedpoints.get((i + 1) % transformedpoints.size()).getY());
				if(i < transformedpoints.size() / 2) {
					grid2.addExtraLine((int)transformedpoints.get(i).getX(), (int)transformedpoints.get(i).getY(), (int)transformedpoints.get((i + 5) % transformedpoints.size()).getX(), (int)transformedpoints.get((i + 5) % transformedpoints.size()).getY());
				}
				else if(i == transformedpoints.size() / 2) {
					grid2.addExtraLine((int)transformedpoints.get(i).getX(), (int)transformedpoints.get(i).getY(), (int)transformedpoints.get(transformedpoints.size() - 1).getX(), (int)transformedpoints.get(transformedpoints.size() - 1).getY());
				}
			}

		new Display(grid2, "Transformed");
	}

	public static void main(String args[]) throws IOException {
		System.out.println("Assignments: ");
		System.out.println("1. Drawing Algorithms");
		System.out.println("2. Transformation Algorithms");
		switch(Viva.getInt("Choose Assignment")) {
			case 1: new Viva().Test_Algorithm(); break;
			case 2: new Viva().Test_Transform(); break;
			default: System.out.println("Invalid input");
		}
	}
}
