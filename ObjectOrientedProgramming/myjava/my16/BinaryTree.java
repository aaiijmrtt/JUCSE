package myjava.my16;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
///class to store and manipulate BinaryTree values
public class BinaryTree extends JFrame implements ActionListener {
	private JPanel[] row; ///<member to hold rows
	private JButton[] button; ///<member to hold buttons
	private static final String[] buttonString = {"Preorder", "Inorder", "Postorder"}; ///<member to hold button strings
	private Font font; ///<member to hold font
	private Vector<Integer> preOrder, ///<member to hold preorder tree traversal indices
		inOrder, ///<member to hold inorder tree traversal indices
		postOrder; ///<member to hold postorder tree traversal indices
	private int size; ///<member to hold size of tree
	///constructor to initialize data members and validate class invariants
	public BinaryTree(int size) {
		super("Binary Tree");
		this.size = size;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(1, 1));
		row = new JPanel[2];
		row[0] = new JPanel();
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		button = new JButton[3];
		for(int i = 0; i < 3; ++i) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
			button[i].setPreferredSize(new Dimension(100, 40));
			row[0].add(button[i]);
		}
		font = new Font("Times new Roman", Font.BOLD, 14);
		add(row[0], BorderLayout.PAGE_START);
		initialize();
		row[1] = new MyJPanel(preOrder);
		add(row[1], BorderLayout.PAGE_END);
		pack();
		setVisible(true);
	}
	///member function to initialize tree traversal indices
	private void initialize() {
		preOrder = new Vector<Integer>(size);
		inOrder = new Vector<Integer>(size);
		postOrder = new Vector<Integer>(size);
		preorder(0);
		inorder(0);
		postorder(0);
	}
	///member function to traverse tree preorder and store indices
	private void preorder(int index) {
		if(index >= size)
			return;
		preOrder.add(index);
		preorder(2 * index + 1);
		preorder(2 * index + 2);
	}
	///member function to traverse tree inorder and store indices
	private void inorder(int index) {
		if(index >= size)
			return;
		inorder(2 * index + 1);
		inOrder.add(index);
		inorder(2 * index + 2);
	}
	///member function to traverse tree postorder and store indices
	private void postorder(int index) {
		if(index >= size)
			return;
		postorder(2 * index + 1);
		postorder(2 * index + 2);
		postOrder.add(index);
	}
	///member function to implement ActionListener
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == button[0]) {
			remove(row[1]);
			row[1] = new MyJPanel(preOrder);
			add(row[1], BorderLayout.PAGE_END);
			revalidate();
			repaint();
			pack();
		}
		else if(ae.getSource() == button[1]) {
			remove(row[1]);
			row[1] = new MyJPanel(inOrder);
			add(row[1], BorderLayout.PAGE_END);
			revalidate();
			repaint();
			pack();
		}
		else if(ae.getSource() == button[2]) {
			remove(row[1]);
			row[1] = new MyJPanel(postOrder);
			add(row[1], BorderLayout.PAGE_END);
			revalidate();
			repaint();
			pack();
		}
	}
	///inner class to store and manipulate MyJPanel values
	class MyJPanel extends JPanel {
	    private int x; ///<member to hold last index traversed
	    private static final int delay = 500; ///<member to hold animation delay
		private Vector<Integer> order; ///<member to hold order of traversal
		///constructor to initialize data members and validate class invariants
	    public MyJPanel(Vector<Integer> traverse) {
			setPreferredSize(new Dimension(300, 300));
			x = 0;
			order = traverse;
			ActionListener counter = new ActionListener() {
				public void actionPerformed(ActionEvent evt) { 
					repaint();
					x++;
				}
			};
			new Timer(delay, counter).start();
	    }
		///member function to calculate y coordinate
		private int y(int n) {
			return (int) (Math.log(n) / Math.log(2));
		}
		///member function to paint panel
	    public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
			g.setColor(Color.WHITE);
			for(int i = 0, value, height, width, reference; i < order.size(); ++i) {
				value = order.get(i) + 1;
				reference = (int) Math.pow(2, y(value));
				height = 20 + y(value) * getHeight() / (y(order.size()) + 1);
				width = (int) ((value + 0.5 - reference) * getWidth() / reference) - 10;
				if(i < x)
					g.fillOval(width, height, 15, 15);
				else
					g.drawOval(width, height, 15, 15);
			}
			for(int i = 1, value1, height1, width1, reference1, value2, height2, width2, reference2; i < order.size(); ++i) {
				value1 = i + 1;
				reference1 = (int) Math.pow(2, y(value1));
				height1 = 27 + y(value1) * getHeight() / (y(order.size()) + 1);
				width1 = (int) ((value1 + 0.5 - reference1) * getWidth() / reference1) - 3;
				value2 = (i + 1) / 2;
				reference2 = (int) Math.pow(2, y(value2));
				height2 = 27 + y(value2) * getHeight() / (y(order.size()) + 1);
				width2 = (int) ((value2 + 0.5 - reference2) * getWidth() / reference2) - 3;
				g.drawLine(width1, height1, width2, height2);
			}
		}
	}
}
