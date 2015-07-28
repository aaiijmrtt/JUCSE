package myjava.my15;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
///class to store and manipulate LinkList values
public class LinkList extends JFrame implements ActionListener {
	private JPanel row0; ///<member to hold top row
	private MyJPanel row1; ///<member to hold bottom row
	private JButton[] button; ///<member to hold buttons
	private static final String[] buttonString = {"Insert Head", "Insert Tail", "Remove Head", "Remove Tail", "Find"}; ///<member to hold button strings
	private JTextArea pad; ///<member to hold text area for input
	private Font font; ///<member to hold font
	///constructor to initialize data members and validate class invariants
	public LinkList() {
		super("Linked List");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(1, 1));
		font = new Font("Times new Roman", Font.BOLD, 14);
		row0 = new JPanel();
		row0.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		button = new JButton[5];
		for(int i = 0; i < 5; ++i) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
			button[i].setPreferredSize(new Dimension(115, 40));
			row0.add(button[i]);
		}
		pad = new JTextArea(1,10);
		row0.add(pad);
		add(row0, BorderLayout.PAGE_START);
		row1 = new MyJPanel();
		add(row1, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
	}
	///member function to implement ActionListener
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == button[0])
			row1.addFirst(Integer.parseInt(pad.getText()));
		else if(ae.getSource() == button[1])
			row1.addLast(Integer.parseInt(pad.getText()));
		else if(ae.getSource() == button[2])
			row1.removeFirst();
		else if(ae.getSource() == button[3])
			row1.removeLast();
		else if(ae.getSource() == button[4])
			row1.find(Integer.parseInt(pad.getText()));
	}
	///inner class to store and manipulate MyJPanel values
	class MyJPanel extends JPanel {
		private LinkedList<Integer> list; ///<member to hold list
		private int find = 0; ///<member to hold index of element found
		private static final int delta = 10; ///<member to hold size of box drawn
		///constructor to initialize data members and validate class invariants
		public MyJPanel() {
			setPreferredSize(new Dimension(700, 100));
			list = new LinkedList<Integer>();
		}
		///member function to find element
		public void find(int integer) {
			find = list.indexOf(integer);
			repaint();
		}
		///member function to add to head
		public void addFirst(int integer) {
			find = -1;
			list.addFirst(integer);
			repaint();
		}
		///member function to add to tail
		public void addLast(int integer) {
			find = -1;
			list.addLast(integer);
			repaint();
		}
		///member function to remove from head
		public void removeFirst() {
			find = -1;
			list.removeFirst();
			repaint();
		}
		///member function to remove from tail
		public void removeLast() {
			find = -1;
			list.removeLast();
			repaint();
		}
		///member function to paint panel
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 700, 100);
			g2.setColor(Color.WHITE);
			int i;
			for (i = 0; i < list.size(); ++i) {
				if(i == find) {
					g2.setColor(Color.BLUE);
					g2.drawRect(delta + i * 30, delta, 20, 20);
					g2.setColor(Color.WHITE);
				}
				else
					g2.drawRect(delta + i * 30, delta, 20, 20);
				g2.drawString("" + list.get(i), 5 + delta + i * 30, 15 + delta);
				if (i != 0)
					g2.fill3DRect(20 + delta + (i -1) * 30, delta + delta / 2, 10, 4, false);
			}
			g2.dispose();
		}
	}
}
