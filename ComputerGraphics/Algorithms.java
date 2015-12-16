import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.Stack;

public class Algorithms {

	//rasterizes a line with given end points, assumed not equal
	public ArrayList<Point> Line_DigitalDifferentialAnalyzer(Point p1, Point p2) {
		ArrayList<Point> points = new ArrayList<Point>();

		double length = Math.max(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
		double dx = (p2.getX() - p1.getX()) / length;
		double dy = (p2.getY() - p1.getY()) / length;
		double x = p1.getX() + 0.5;
		double y = p1.getY() + 0.5;

		for(int i = 0; i <= length; ++i) {
			points.add(new Point((int)Math.floor(x), (int)Math.floor(y)));
			x += dx;
			y += dy;
		}

		return points;
	}

	//rasterizes a line with given end points, assumed not equal, assumed 1st octant
	public ArrayList<Point> Line_Bresenham(Point p1, Point p2) {
		ArrayList<Point> points = new ArrayList<Point>();

		double dx = p2.getX() - p1.getX();
		double dy = p2.getY() - p1.getY();
		double x = p1.getX();
		double y = p1.getY();
		double m = dy / dx;
		double e = m - 0.5;

		for(int i = 0; i <= dx; ++i) {
			points.add(new Point((int)Math.floor(x), (int)Math.floor(y)));
			while(e > 0.0) {
				y += 1;
				e -= 1;
			}
			x += 1;
			e += m;
		}

		return points;
	}

	//rasterizes a line with given end points, assumed not equal, assumed 1st octant
	public ArrayList<Point> Line_BresenhamInteger(Point p1, Point p2) {
		ArrayList<Point> points = new ArrayList<Point>();

		int dx = (int)(p2.getX() - p1.getX());
		int dy = (int)(p2.getY() - p1.getY());
		int x = (int)p1.getX();
		int y = (int)p1.getY();
		int e = 2 * dy - dx;

		for(int i = 0; i <= dx; ++i) {
			points.add(new Point(x, y));
			while(e > 0) {
				y += 1;
				e -= 2 * dx;
			}
			x += 1;
			e += 2 * dy;
		}

		return points;
	}

	private int Sign(double x) {
		if(x > 0.0)
			return 1;
		else if (x < 0.0)
			return -1;
		else
			return 0;
	}

	//rasterizes a line with given end points, assumed not equal
	public ArrayList<Point> Line_BresenhamIntegerGeneralized(Point p1, Point p2) {
		ArrayList<Point> points = new ArrayList<Point>();

		int dx = (int)Math.abs(p2.getX() - p1.getX());
		int dy = (int)Math.abs(p2.getY() - p1.getY());
		int x = (int)p1.getX();
		int y = (int)p1.getY();
		int s1 = (int)Sign(p2.getX() - p1.getX());
		int s2 = (int)Sign(p2.getY() - p1.getY());
		int e = 2 * dy - dx;

		boolean interchange = dy > dx;
		if(interchange) {
			dx ^= dy;
			dy ^= dx;
			dx ^= dy;
		}

		for(int i = 0; i <= dx; ++i) {
			points.add(new Point(x, y));
			while(e > 0) {
				if(interchange)
					x += s1;
				else
					y += s2;
				e -= 2 * dx;
			}
			if(interchange)
				y += s2;
			else
				x += s1;
			e += 2 * dy;
		}

		return points;
	}

	//rasterizes a line with given end points, assumed not equal, assumed 1st octant
	public ArrayList<Point> Line_Midpoint(Point p1, Point p2) {
		ArrayList<Point> points = new ArrayList<Point>();

		int dx = (int)(p2.getX() - p1.getX());
		int dy = (int)(p2.getY() - p1.getY());
		int x = (int)p1.getX();
		int y = (int)p1.getY();
		points.add(new Point(x, y));

		for(; x < (int)p2.getX(); ++x) {
			if(2 * dy * (x + 1) + 2 * (int)(p2.getX() * p1.getY() - p1.getX() * p2.getY()) - dx * (2 * y + 1) > 0)
				++y;
			points.add(new Point(x + 1, y));
		}

		return points;
	}

	//rasterizes a line with given end points, assumed not equal, assumed 1st octant
	public ArrayList<Point> Line_MidpointFirstOrder(Point p1, Point p2) {
		ArrayList<Point> points = new ArrayList<Point>();

		int dx = (int)(p2.getX() - p1.getX());
		int dy = (int)(p2.getY() - p1.getY());
		int x = (int)p1.getX();
		int y = (int)p1.getY();
		int d = 2 * dy - dx;
		points.add(new Point(x, y));

		for(; x < (int)p2.getX(); ++x) {
			if(d > 0) {
				d += 2 * dy - 2 * dx;
				++y;
			}
			else
				d += 2 * dy;
			points.add(new Point(x + 1, y));
		}

		return points;
	}

	public ArrayList<Point> ShiftOrigin(Point neworigin, ArrayList<Point> points) {
		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)(neworigin.getX() + point.getX()), (int)(neworigin.getY() + point.getY())));
		return newpoints;
	}

	//rasterizes a circle with given radius, assumed centred at the origin
	public ArrayList<Point> Circle_Midpoint(int radius) {
		ArrayList<Point> points = new ArrayList<Point>();

		int x = 0;
		int y = radius;
		points.add(new Point(x, y));

		for(++x; x <= y; ++x) {
			if(radius * radius < x * x + (y - 0.5) * (y - 0.5))
				--y;
			points.add(new Point(x, y));
		}

		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)point.getY(), (int)point.getX()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point((int)point.getX(), -(int)point.getY()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point(-(int)point.getX(), (int)point.getY()));
		points.addAll(newpoints);

		return points;
	}

	//rasterizes a circle with given radius, assumed centred at the origin
	public ArrayList<Point> Circle_Bresenham(int radius) {
		ArrayList<Point> points = new ArrayList<Point>();

		int x = 0;
		int y = radius;
		int d = 2 * (1 - radius);
		points.add(new Point(x, y));

		while(y >= 0) {
			if(d > 0) {
				y--;
				if(2 * d - 2 * x + 1 > 0) 
					d += - 2 * y + 1;
				else {
					x++;
					d += 2 * x + 2 - 2 * y;
				}
			}
			else if(d < 0) {
				x++;
				if(2 * d + 2 * y < 1)
					d+= 2 * x + 1;
				else {
					y--;
					d += 2 * x - 2 * y + 2;
				}
			}
			else {
				x++;
				y--;
				d += 2 * x - 2 * y + 2;
			}
			points.add(new Point(x, y));
		}

		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)point.getX(), -(int)point.getY()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point(-(int)point.getX(), (int)point.getY()));
		points.addAll(newpoints);

		return points;
	}

	//rasterizes a circle with given radius, assumed centred at the origin
	public ArrayList<Point> Circle_MidpointFirstOrder(int radius) {
		ArrayList<Point> points = new ArrayList<Point>();

		int x = 0;
		int y = radius;
		int d = 1 - radius;
		points.add(new Point(x, y));

		while(y >= x) {
			if(d >= 0) {
				d += 2 * x - 2 * y + 5;
				y--;
			}
			else
				d += 2 * x + 3;
			x++;
			points.add(new Point(x, y));
		}

		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)point.getY(), (int)point.getX()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point((int)point.getX(), -(int)point.getY()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point(-(int)point.getX(), (int)point.getY()));
		points.addAll(newpoints);

		return points;
	}

	//rasterizes a circle with given radius, assumed centred at the origin
	public ArrayList<Point> Circle_MidpointSecondOrder(int radius) {
		ArrayList<Point> points = new ArrayList<Point>();

		int x = 0;
		int y = radius;
		int d = 1 - radius;
		int de = 3;
		int dse = 5 - 2 * radius;
		int dee = 2;
		int dese = 2;
		int dsee = 2;
		int dsese = 4;
		points.add(new Point(x, y));

		while(y >= x) {
			if(d >= 0) {
				y--;
				d += dse;
				dse += dsese;
				de += dese;
			}
			else {
				d += de;
				de += dee;
				dse += dsee;
			}
			x++;
			points.add(new Point(x, y));
		}

		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)point.getY(), (int)point.getX()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point((int)point.getX(), -(int)point.getY()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point(-(int)point.getX(), (int)point.getY()));
		points.addAll(newpoints);

		return points;
	}

	//rasterizes an ellipse with given radius, assumed centred at the origin
	public ArrayList<Point> Ellipse_Midpoint(int semimajoraxis, int semiminoraxis) {
		ArrayList<Point> points = new ArrayList<Point>();

		int a2 = semimajoraxis * semimajoraxis;
		int b2 = semiminoraxis * semiminoraxis;

		int x = 0;
		int y = semiminoraxis;
		points.add(new Point(x, y));

		for(++x; b2 * x <= a2 * y; ++x) {
			if(a2 * b2 < b2 * x * x + a2 * (y - 0.5) * (y - 0.5))
				--y;
			points.add(new Point(x, y));
		}

		x = semimajoraxis;
		y = 0;
		points.add(new Point(x, y));

		for(++y; a2 * y <= b2 * x; ++y) {
			if(a2 * b2 < b2 * (x - 0.5) * (x - 0.5)  + a2 * y * y)
				--x;
			points.add(new Point(x, y));
		}

		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)point.getX(), -(int)point.getY()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point(-(int)point.getX(), (int)point.getY()));
		points.addAll(newpoints);

		return points;
	}

	//rasterizes a circle with given radius, assumed centred at the origin
	public ArrayList<Point> Ellipse_MidpointFirstOrder(int semimajoraxis, int semiminoraxis) {
		ArrayList<Point> points = new ArrayList<Point>();

		int a2 = semimajoraxis * semimajoraxis;
		int b2 = semiminoraxis * semiminoraxis;

		int x = 0;
		int y = semiminoraxis;
		points.add(new Point(x, y));
		int d = 4 * (b2 - a2 * semiminoraxis) + a2;

		while(a2 * (2 * y - 1) * (2 * y - 1) >= 4 * b2 * (x + 1) * (x + 1)) {
			if(d > 0) {
				d += 4 * (b2 * (2 * x + 3) + a2 * (2 - 2 * y));
				y--;
			}
			else
				d += 4 * b2 * (2 * x + 3);
			x++;
			points.add(new Point(x, y));
		}

		d = b2 * (2 * x + 1) * (2 * x + 1) + 4 * a2 * ((y - 1) * (y - 1) - b2);

		while(y >= 0) {
			if(d <= 0) {
				d += 4 * (2 * b2 * (x + 1) + a2 * (3 - 2 * y));
				x++;
			}
			else
				d += 4 * a2 * (3 - 2 * y);
			y--;
			points.add(new Point(x, y));
		}

		ArrayList<Point> newpoints = new ArrayList<Point>();
		for(Point point: points)
			newpoints.add(new Point((int)point.getX(), -(int)point.getY()));
		points.addAll(newpoints);

		newpoints.clear();
		for(Point point: points)
			newpoints.add(new Point(-(int)point.getX(), (int)point.getY()));
		points.addAll(newpoints);

		return points;
	}

	private ArrayList<Point> scanConvert(Point point1, Point point2) {
		ArrayList<Point> points = new ArrayList<Point>();
		if(point1.getY() < point2.getY())
			for(int i = (int)point1.getY(); i < (int)point2.getY(); ++i)
				points.add(new Point((int)(point1.getX() + (i - point1.getY()) * (point2.getX() - point1.getX()) / (point2.getY() - point1.getY())), i));
		else if(point1.getY() > point2.getY())
			for(int i = (int)point1.getY(); i > (int)point2.getY(); --i)
				points.add(new Point((int)(point1.getX() + (i - point1.getY()) * (point2.getX() - point1.getX()) / (point2.getY() - point1.getY())), i));
		return points;
	}

	public Comparator<Point> comparator = new Comparator<Point>() {
		public int compare(Point point1, Point point2) {
			if(point1.getY() < point2.getY())
				return -1;
			else if(point1.getY() > point2.getY())
				return 1;
			else if(point1.getX() < point2.getX())
				return -1;
			else if(point1.getX() > point2.getX())
				return 1;
			else
				return 0;
		}
	};

	//fills a polygon given by a set of ordered vertices
	public ArrayList<Point> Polygon_OrderedEdgeList(ArrayList<Point> vertices) {
		ArrayList<Point> points = new ArrayList<Point>(), newpoints = new ArrayList<Point>();

		int i, j, y;
		for(i = 0; i < vertices.size(); ++i) {
			points.addAll(scanConvert(vertices.get(i), vertices.get((i + 1) % vertices.size())));
			if((vertices.get(i).getY() - vertices.get((i + 1) % vertices.size()).getY()) * (vertices.get(i).getY() - vertices.get((i - 1 + vertices.size()) % vertices.size()).getY()) > 0)
				points.add(vertices.get(i));
		}
		Collections.sort(points, comparator);

		boolean parity;
		for(i = 0; i < points.size(); ++i) {
			y = (int)points.get(i).getY();
			parity = true;
			while(i < points.size() - 1 && y == (int)points.get(i+1).getY()) {
				if(parity)
					for(j = (int)points.get(i).getX(); j <= (int)points.get(i+1).getX(); ++j)
						newpoints.add(new Point(j, y));
				parity = !parity;
				++i;
			}
		}
		return newpoints;
	}

	//fills a polygon given by a set of ordered vertices
	public ArrayList<Point> Polygon_EdgeFill(ArrayList<Point> vertices) {
		ArrayList<Point> points = new ArrayList<Point>(), newpoints = new ArrayList<Point>();

		int i, j, y, minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
		Point point, point1, point2;
		for(i = 0; i < vertices.size(); ++i) {
			point1 = vertices.get(i);
			point2 = vertices.get((i + 1) % vertices.size());
			minx = (int)Math.min(point1.getX(), minx);
			maxx = (int)Math.max(point1.getX(), maxx);
			miny = (int)Math.min(point1.getY(), miny);
			maxy = (int)Math.max(point1.getY(), maxy);
			points.addAll(scanConvert(point1, point2));
			if((vertices.get(i).getY() - vertices.get((i + 1) % vertices.size()).getY()) * (vertices.get(i).getY() - vertices.get((i - 1 + vertices.size()) % vertices.size()).getY()) >= 0)
				points.remove(vertices.get(i));
		}
		Collections.sort(points, comparator);

		boolean fill[][] = new boolean [maxx - minx + 1][maxy - miny + 1];
		for(i = 0; i < points.size(); ++i) {
			point = points.get(i);
			y = (int)(point.getY() - miny);
			for(j = (int)(point.getX() - minx + 1); j < fill.length; ++j)
				fill[j][y] = !fill[j][y];
		}

		for(i = 0; i < fill.length; ++i)
			for(j = 0; j < fill[i].length; ++j)
				if(fill[i][j])
					newpoints.add(new Point(i + minx, j + miny));
		return newpoints;
	}

	//fills a polygon given by a set of ordered vertices
	public ArrayList<Point> Polygon_FenceFill(ArrayList<Point> vertices) {
		ArrayList<Point> points = new ArrayList<Point>(), newpoints = new ArrayList<Point>();

		int i, j, y, minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE, fence;
		Point point, point1, point2;
		for(i = 0; i < vertices.size(); ++i) {
			point1 = vertices.get(i);
			point2 = vertices.get((i + 1) % vertices.size());
			minx = (int)Math.min(point1.getX(), minx);
			maxx = (int)Math.max(point1.getX(), maxx);
			miny = (int)Math.min(point1.getY(), miny);
			maxy = (int)Math.max(point1.getY(), maxy);
			points.addAll(scanConvert(point1, point2));
			if((vertices.get(i).getY() - vertices.get((i + 1) % vertices.size()).getY()) * (vertices.get(i).getY() - vertices.get((i - 1 + vertices.size()) % vertices.size()).getY()) >= 0)
				points.remove(vertices.get(i));
		}
		Collections.sort(points, comparator);

		boolean fill[][] = new boolean [maxx - minx + 1][maxy - miny + 1];
		fence = (maxx + minx) / 2;
		for(i = 0; i < points.size(); ++i) {
			point = points.get(i);
			y = (int)(point.getY() - miny);
			if(point.getX() < fence)
				for(j = (int)(point.getX() - minx + 1); j < fence - minx; ++j)
					fill[j][y] = !fill[j][y];
			else
				for(j = fence - minx; j <= (int)(point.getX() - minx); ++j)
					fill[j][y] = !fill[j][y];
		}

		for(i = 0; i < fill.length; ++i)
			for(j = 0; j < fill[i].length; ++j)
				if(fill[i][j])
					newpoints.add(new Point(i + minx, j + miny));
		return newpoints;
	}

	//fills a polygon given by a set of ordered vertices
	public ArrayList<Point> Polygon_EdgeFlag(ArrayList<Point> vertices) {
		ArrayList<Point> points = new ArrayList<Point>(), newpoints = new ArrayList<Point>();

		int i, j, y;
		Point point, point1, point2;
		for(i = 0; i < vertices.size(); ++i) {
			points.addAll(scanConvert(vertices.get(i), vertices.get((i + 1) % vertices.size())));
			if((vertices.get(i).getY() - vertices.get((i + 1) % vertices.size()).getY()) * (vertices.get(i).getY() - vertices.get((i - 1 + vertices.size()) % vertices.size()).getY()) > 0)
				points.add(vertices.get(i));
		}
		Collections.sort(points, comparator);

		boolean inside;
		for(i = 0; i < points.size(); ++i) {
			y = (int)points.get(i).getY();
			inside = true;
			while(i < points.size() - 1 && y == (int)points.get(i+1).getY()) {
				if(inside)
					for(j = (int)points.get(i).getX(); j <= (int)points.get(i+1).getX(); ++j)
						newpoints.add(new Point(j, y));
				inside = !inside;
				++i;
			}
		}

		return newpoints;
	}

	//fills a polygon given by a set of ordered vertices, assumed seed is within its boundary
	public ArrayList<Point> Polygon_SeedFillSimple(ArrayList<Point> vertices, Point seed) {
		ArrayList<Point> points = new ArrayList<Point>(), newpoints = new ArrayList<Point>();
		Stack<Point> stack = new Stack<Point>();

		int i, j, y, minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
		Point point, point1, point2, pointnorth, pointsouth, pointeast, pointwest;
		for(i = 0; i < vertices.size(); ++i) {
			point1 = vertices.get(i);
			point2 = vertices.get((i + 1) % vertices.size());
			minx = (int)Math.min(point1.getX(), minx);
			maxx = (int)Math.max(point1.getX(), maxx);
			miny = (int)Math.min(point1.getY(), miny);
			maxy = (int)Math.max(point1.getY(), maxy);
			points.addAll(Line_BresenhamIntegerGeneralized(point1, point2));
		}
		Collections.sort(points, comparator);

		boolean checked[][] = new boolean [maxx - minx + 1][maxy - miny + 1];
		stack.push(seed);

		while(!stack.empty()) {
			point = stack.pop();
			checked[(int)(point.getX() - minx)][(int)(point.getY() - miny)] = true;
			if(!newpoints.contains(point))
				newpoints.add(point);

			pointnorth = new Point((int)point.getX(), (int)point.getY() + 1);
			pointsouth = new Point((int)point.getX(), (int)point.getY() - 1);
			pointeast = new Point((int)point.getX() + 1, (int)point.getY());
			pointwest = new Point((int)point.getX() - 1, (int)point.getY());

			if(!checked[(int)(pointnorth.getX() - minx)][(int)(pointnorth.getY() - miny)] && !points.contains(pointnorth))
				stack.push(pointnorth);
			if(!checked[(int)pointsouth.getX() - minx][(int)pointsouth.getY() - miny] && !points.contains(pointsouth))
				stack.push(pointsouth);
			if(!checked[(int)pointeast.getX() - minx][(int)pointeast.getY() - miny] && !points.contains(pointeast))
				stack.push(pointeast);
			if(!checked[(int)pointwest.getX() - minx][(int)pointwest.getY()- miny] && !points.contains(pointwest))
				stack.push(pointwest);
		}
		return newpoints;
	}

	//fills a polygon given by a set of ordered vertices, assumed seed is within its boundary
	public ArrayList<Point> Polygon_SeedFillScanLine(ArrayList<Point> vertices, Point seed) {
		ArrayList<Point> points = new ArrayList<Point>(), newpoints = new ArrayList<Point>();
		Stack<Point> stack = new Stack<Point>();

		int i, j, y, minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
		Point point, point1, point2, pointright, pointleft, pointeast, pointwest;
		for(i = 0; i < vertices.size(); ++i) {
			point1 = vertices.get(i);
			point2 = vertices.get((i + 1) % vertices.size());
			minx = (int)Math.min(point1.getX(), minx);
			maxx = (int)Math.max(point1.getX(), maxx);
			miny = (int)Math.min(point1.getY(), miny);
			maxy = (int)Math.max(point1.getY(), maxy);
			points.addAll(Line_BresenhamIntegerGeneralized(point1, point2));
		}
		Collections.sort(points, comparator);

		boolean checked[][] = new boolean [maxx - minx + 1][maxy - miny + 1];
		stack.push(seed);

		while(!stack.empty()) {
			point = stack.pop();
			checked[(int)(point.getX() - minx)][(int)(point.getY() - miny)] = true;
			if(!newpoints.contains(point))
				newpoints.add(point);

			pointright = new Point((int)point.getX() + 1, (int)point.getY());
			while(!points.contains(pointright)) {
				checked[(int)(pointright.getX() - minx)][(int)(pointright.getY() - miny)] = true;
				if(!newpoints.contains(pointright))
					newpoints.add(pointright);
				pointright = new Point((int)pointright.getX() + 1, (int)pointright.getY());
			}
			pointeast = new Point((int)pointright.getX() - 1, (int)pointright.getY());

			pointleft = new Point((int)point.getX() - 1, (int)point.getY());
			while(!points.contains(pointleft)) {
				checked[(int)(pointleft.getX() - minx)][(int)(pointleft.getY() - miny)] = true;
				if(!newpoints.contains(pointleft))
					newpoints.add(pointleft);
				pointleft = new Point((int)pointleft.getX() - 1, (int)pointleft.getY());
			}
			pointwest = new Point((int)pointleft.getX() + 1, (int)pointleft.getY());

			pointleft = new Point((int)pointwest.getX(), (int)pointwest.getY() + 1);
			pointright = new Point((int)pointeast.getX(), (int)pointeast.getY() + 1);
			while(comparator.compare(pointleft, pointright) <= 0) {
				while(comparator.compare(pointleft, pointright) < 0 && (points.contains(pointleft) || checked[(int)pointleft.getX() - minx][(int)pointleft.getY() - miny]))
					pointleft = new Point((int)pointleft.getX() + 1, (int)pointleft.getY());
				if(comparator.compare(pointleft, pointright) == 0)
					break;
				stack.push(pointleft);
				while(comparator.compare(pointleft, pointright) < 0 && !(points.contains(pointleft) || checked[(int)pointleft.getX() - minx][(int)pointleft.getY() - miny]))
					pointleft = new Point((int)pointleft.getX() + 1, (int)pointleft.getY());
			}

			pointleft = new Point((int)pointwest.getX(), (int)pointwest.getY() - 1);
			pointright = new Point((int)pointeast.getX(), (int)pointeast.getY() - 1);
			while(comparator.compare(pointleft, pointright) <= 0) {
				while(comparator.compare(pointleft, pointright) < 0 && (points.contains(pointleft) || checked[(int)pointleft.getX() - minx][(int)pointleft.getY() - miny]))
					pointleft = new Point((int)pointleft.getX() + 1, (int)pointleft.getY());
				if(comparator.compare(pointleft, pointright) == 0)
					break;
				stack.push(pointleft);
				while(comparator.compare(pointleft, pointright) < 0 && !(points.contains(pointleft) || checked[(int)pointleft.getX() - minx][(int)pointleft.getY() - miny]))
					pointleft = new Point((int)pointleft.getX() + 1, (int)pointleft.getY());
			}
		}
		return newpoints;
	}

	private byte CohenSutherlandEndPointCode(Point p, int lowerx, int higherx, int lowery, int highery) {
		byte code = 0;
		if(p.getX() < lowerx)
			code |= 1;
		else if(p.getX() > higherx)
			code |= 2;
		if(p.getY() < lowery)
			code |= 4;
		else if(p.getY() > highery)
			code |= 8;
		return code;
	}

	//clips a line against a rectangular boundary parallel to the axes
	public ArrayList<Point> Clipping_CohenSutherland(Point p1, Point p2, int lowerx, int higherx, int lowery, int highery) {
		byte code1 = CohenSutherlandEndPointCode(p1, lowerx, higherx, lowery, highery);
		byte code2 = CohenSutherlandEndPointCode(p2, lowerx, higherx, lowery, highery);
		byte tempcode;
		Point temppoint;
		if((code1 & code2) != 0)
			return new ArrayList<Point>();
		if((code1 | code2) == 0)
			return Line_BresenhamIntegerGeneralized(p1, p2);

		if((code2 & 1) != 0) {
			tempcode = code1;
			code1 = code2;
			code2 = tempcode;
			temppoint = p1;
			p1 = p2;
			p2 = temppoint;
		}
		if((code1 & 1) != 0) {
			p1 = new Point(lowerx, (int)(p1.getY() + (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()) * (lowerx - p1.getX())));
			code1 = CohenSutherlandEndPointCode(p1, lowerx, higherx, lowery, highery);
		}
		if((code1 & code2) != 0)
			return new ArrayList<Point>();
		if((code1 | code2) == 0)
			return Line_BresenhamIntegerGeneralized(p1, p2);

		if((code2 & 2) != 0) {
			tempcode = code1;
			code1 = code2;
			code2 = tempcode;
			temppoint = p1;
			p1 = p2;
			p2 = temppoint;
		}
		if((code1 & 2) != 0) {
			p1 = new Point(higherx, (int)(p1.getY() + (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()) * (higherx - p1.getX())));
			code1 = CohenSutherlandEndPointCode(p1, lowerx, higherx, lowery, highery);
		}
		if((code1 & code2) != 0)
			return new ArrayList<Point>();
		if((code1 | code2) == 0)
			return Line_BresenhamIntegerGeneralized(p1, p2);

		if((code2 & 4) != 0) {
			tempcode = code1;
			code1 = code2;
			code2 = tempcode;
			temppoint = p1;
			p1 = p2;
			p2 = temppoint;
		}
		if((code1 & 4) != 0) {
			p1 = new Point((int)(p1.getX() + (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) * (lowery - p1.getY())), lowery);
			code1 = CohenSutherlandEndPointCode(p1, lowerx, higherx, lowery, highery);
		}
		if((code1 & code2) != 0)
			return new ArrayList<Point>();
		if((code1 | code2) == 0)
			return Line_BresenhamIntegerGeneralized(p1, p2);

		if((code2 & 8) != 0) {
			tempcode = code1;
			code1 = code2;
			code2 = tempcode;
			temppoint = p1;
			p1 = p2;
			p2 = temppoint;
		}
		if((code1 & 8) != 0) {
			p1 = new Point((int)(p1.getX() + (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) * (highery - p1.getY())), highery);
			code1 = CohenSutherlandEndPointCode(p1, lowerx, higherx, lowery, highery);
		}
		if((code1 & code2) != 0)
			return new ArrayList<Point>();
		if((code1 | code2) == 0)
			return Line_BresenhamIntegerGeneralized(p1, p2);

		return new ArrayList<Point>();
	}

	//clips a line against a polygonal boundary given by a counterclockwise ordered set of vertices
	public ArrayList<Point> Clipping_CyrusBeck(Point p1, Point p2, ArrayList<Point> boundary) {
		double upper = 1.0, lower = 0.0, dot1, dot2;
		Point boundarypoint1, boundarypoint2, normal, vector, direction = new Point((int)(p2.getX() - p1.getX()), (int)(p2.getY() - p1.getY()));
		for(int i = 0; i < boundary.size(); ++i) {
			boundarypoint1 = boundary.get(i);
			boundarypoint2 = boundary.get((i + 1) % boundary.size());
			normal = new Point((int)(boundarypoint1.getY() - boundarypoint2.getY()), (int)(boundarypoint2.getX() - boundarypoint1.getX()));
			vector = new Point((int)(-boundarypoint1.getX() + p1.getX()), (int)(-boundarypoint1.getY() + p1.getY()));
			dot1 = normal.getX() * direction.getX() + normal.getY() * direction.getY();
			dot2 = vector.getX() * normal.getX() + vector.getY() * normal.getY();
			if(dot1 > 0)
				lower = Math.max(lower, -dot2 / dot1);
			else if(dot1 < 0)
				upper = Math.min(upper, -dot2 / dot1);
			else if(dot2 < 0)
				return new ArrayList<Point>();
		}
		if(lower <= upper) {
			boundarypoint1 = new Point((int)(p1.getX() + lower * direction.getX()), (int)(p1.getY() + lower * direction.getY()));
			boundarypoint2 = new Point((int)(p1.getX() + upper * direction.getX()), (int)(p1.getY() + upper * direction.getY()));
			return Line_BresenhamIntegerGeneralized(boundarypoint1, boundarypoint2);
		}
		return new ArrayList<Point>();
	}

}
