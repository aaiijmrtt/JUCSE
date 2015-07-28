package myjava.my12;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.math.BigInteger;
///class to store and manipulate Calculator values
public class Calculator extends JFrame implements ActionListener {
	private JPanel[] row; ///<member to hold rows
	private JButton[] button; ///<member to hold buttons
	private static final String[] buttonString = {"x+y", "x-y", "x*y", "x/y", "x**y", "x%y", "-x", "-y", "x<->y", "x&y", "x|y", "x^y", "set_y(x)", "clr_y(x)", "gcd(x,y)", "nxt_prm(x)"}; ///<member to hold button strings
	private JTextArea x, ///<members to hold text area for input x
		y, ///<members to hold text area for input y
		z; ///<members to hold text area for output z
	private JLabel lx, ///<member to hold label for input x
		ly; ///<member to hold label for input y
	private Font font; ///<member to hold font
	///constructor to initialize data members and validate class invariants
	public Calculator() {
		super("Calculator");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(7,5);
		setLayout(grid);
		row = new JPanel[7];
		button = new JButton[16];
		x = new JTextArea(1,23);
		lx = new JLabel("X");
		y = new JTextArea(1,23);
		ly = new JLabel("Y");
		z = new JTextArea(1,25);
		font = new Font("Times new Roman", Font.BOLD, 14);
		for(int i = 0; i < 7; i++)
			row[i] = new JPanel();
		for(int i = 0; i < 16; i++) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		row[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		row[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i = 3; i < 7; i++)
			row[i].setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		x.setFont(font);
		x.setEditable(true);
		x.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		y.setFont(font);
		y.setEditable(true);
		y.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		z.setFont(font);
		z.setEditable(false);
		z.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for(int i = 0; i < 16; i++)
			button[i].setPreferredSize(i/4 == 3 ? new Dimension(100, 40) : new Dimension(75, 40));
		row[0].add(z);
		row[1].add(lx);
		row[1].add(x);
		row[2].add(ly);
		row[2].add(y);
		for(int i = 0; i < 16; i++)
			row[3+i%4].add(button[i]);
		for(int i = 0; i < 7; ++i)
			add(row[i]);
		pack();
		setVisible(true);
	}
	///member function to implement ActionListener
	public void actionPerformed(ActionEvent ae) {
		try {
			BigInteger a = new BigInteger(x.getText());
			BigInteger b = new BigInteger(y.getText());
			if(ae.getSource() == button[0])
				z.setText(a.add(b).toString());
			else if(ae.getSource() == button[1])
				z.setText(a.subtract(b).toString());
			else if(ae.getSource() == button[2])
				z.setText(a.multiply(b).toString());
			else if(ae.getSource() == button[3])
				z.setText(a.divide(b).toString());
			else if(ae.getSource() == button[4])
				z.setText(a.pow(b.intValue()).toString());
			else if(ae.getSource() == button[5])
				z.setText(a.remainder(b).toString());
			else if(ae.getSource() == button[6])
				x.setText(a.negate().toString());
			else if(ae.getSource() == button[7])
				y.setText(b.negate().toString());
			else if(ae.getSource() == button[8]) {
				x.setText(b.toString());
				y.setText(a.toString());
			}
			else if(ae.getSource() == button[9])
				z.setText(a.and(b).toString());
			else if(ae.getSource() == button[10])
				z.setText(a.or(b).toString());
			else if(ae.getSource() == button[11])
				z.setText(a.xor(b).toString());
			else if(ae.getSource() == button[12])
				z.setText(a.setBit(b.intValue()).toString());
			else if(ae.getSource() == button[13])
				z.setText(a.clearBit(b.intValue()).toString());
			else if(ae.getSource() == button[14])
				z.setText(a.gcd(b).toString());
			else if(ae.getSource() == button[15])
				z.setText(a.nextProbablePrime().toString());
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
