import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Grid extends JPanel {

	class Extra {

		private int attribute1, attribute2, attribute3, attribute4;

		public Extra(int attribute1, int attribute2, int attribute3, int attribute4) {
			this.attribute1 = attribute1;
			this.attribute2 = attribute2;
			this.attribute3 = attribute3;
			this.attribute4 = attribute4;
		}

		public int get1() {
			return attribute1;
		}

		public int get2() {
			return attribute2;
		}

		public int get3() {
			return attribute3;
		}

		public int get4() {
			return attribute4;
		}

	}

	private ArrayList<Point> pixels;
	private ArrayList<Extra> extraovals, extralines;
	private int height, width, division, originx, originy;
	private int animate;
	private boolean grid;

	public Grid(int width, int height, int division, int originx, int originy) {
		this(width, height, division, originx, originy, false, true);
	}

	public Grid(int width, int height, int division, int originx, int originy, boolean anime) {
		this(width, height, division, originx, originy, anime, true);
	}

	public Grid(int width, int height, int division, int originx, int originy, boolean anime, boolean grid) {
		this.height = height;
		this.width = width;
		this.division = division;
		this.originx = originx - division / 2;
		this.originy = originy + division / 2;
		this.grid = grid;
		pixels = new ArrayList<Point>();
		extralines = new ArrayList<Extra>();
		extraovals = new ArrayList<Extra>();
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		if(anime) {
			this.animate = 0;
			ActionListener counter = new ActionListener() {
				public void actionPerformed(ActionEvent evt) { 
					repaint();
					animate = (animate + 1) % pixels.size();
				}
			};
			new Timer(50, counter).start();
		}
		else
			this.animate = -1;
	}

	public void addExtraOval(int attribute1, int attribute2, int attribute3, int attribute4) {
		extraovals.add(new Extra(attribute1, attribute2, attribute3, attribute4));
	}

	public void addExtraLine(int attribute1, int attribute2, int attribute3, int attribute4) {
		extralines.add(new Extra(attribute1, attribute2, attribute3, attribute4));
	}

	public void setPixel(Point pixel) {
		pixels.add(pixel);
	}

	public void unsetPixel(Point pixel) {
		pixels.remove(pixel);
	}

	public void setPixels(ArrayList<Point> pixels) {
		for(Point pixel: pixels)
			this.pixels.add(pixel);
	}

	public void unsetPixels(ArrayList<Point> pixels) {
		for(Point pixel: pixels)
			this.pixels.remove(pixel);
	}

	public void clear() {
		pixels.clear();
		extraovals.clear();
		extralines.clear();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(grid) {
			g.setColor(Color.BLUE);
			g.drawLine(originx + division / 2, 0, originx + division / 2, height);
			g.drawLine(0, originy - division / 2, width, originy - division / 2);

			g.setColor(Color.WHITE);
			for(int i = originx % division; i < width; i += division)
				g.drawLine(i, 0, i, height);
			for(int i = originy % division; i < height; i += division)
				g.drawLine(0, i, width, i);
		}

		g.setColor(Color.WHITE);
		if(animate >= 0)
			for(int i = 0; i <= animate; ++i) {
				Point pixel = pixels.get(i);
				g.fillRect(originx + (int)pixel.getX() * division, originy - ((int)pixel.getY() + 1) * division, division, division);
			}
		else
			for(Point pixel: pixels)
				g.fillRect(originx + (int)pixel.getX() * division, originy - ((int)pixel.getY() + 1) * division, division, division);

		g.setColor(Color.YELLOW);
		for(Extra extraline: extralines)
			g.drawLine(originx + (int)extraline.get1() * division + division / 2, originy - ((int)extraline.get2() + 1) * division + division / 2,
				originx + (int)extraline.get3() * division + division / 2, originy - ((int)extraline.get4() + 1) * division + division / 2);

		for(Extra extraoval: extraovals)
			g.drawOval(originx + (int)extraoval.get1() * division + division / 2, originy - ((int)extraoval.get2() + 1) * division + division / 2,
				(int)extraoval.get3() * division, (int)extraoval.get4() * division);
	}

}

