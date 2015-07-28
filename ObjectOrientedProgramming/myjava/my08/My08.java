package myjava.my08;
import myjava.my07.MultiQuote;
import java.awt.event.*;
import java.awt.*;
import java.applet.*;

public class My08 extends Applet implements MouseListener {
	private MultiQuote multiQuote;
	private String quote;
	///member function to initialize applet
	public void init() {
		try {
			multiQuote = new MultiQuote("./myjava/my06/Quote.txt");
			addMouseListener(this);
			quote = null;
		}
		catch(Exception e) {
			System.out.println(e.toString() + ": try again");
		}
	}
	///member function to paint applet
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		g.setColor(Color.WHITE);
		int i = 0;
		for(String line : quote.split("\n")) {
			g.drawString(line, 30, 30 + i * g.getFontMetrics().getHeight());
			++i;
		}
	}
	///member function to start applet
	public void start() {
		try {
			quote = multiQuote.readRandom();
		}
		catch(Exception e) {
			System.out.println(e.toString() + ": try again");
		}
		repaint();
	}
	///member function to stop applet
	public void stop() {
	}
	///member function to destroy applet
	public void destroy() {
		multiQuote = null;
		quote = null;
	}
	///member function to implement MouseListener
	public void mouseEntered(MouseEvent me) {
	}
	///member function to implement MouseListener
	public void mouseExited(MouseEvent me) {
	}
	///member function to implement MouseListener
	public void mousePressed(MouseEvent me) {
	}
	///member function to implement MouseListener
	public void mouseReleased(MouseEvent me) {
	}
	///member function to implement MouseListener
	public void mouseClicked(MouseEvent me) {
		try {
			quote = multiQuote.readRandom();
		}
		catch(Exception e) {
			System.out.println(e.toString() + ": try again");
		}
		repaint();
	}
}
