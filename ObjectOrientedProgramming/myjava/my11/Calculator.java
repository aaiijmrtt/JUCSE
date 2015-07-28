package myjava.my11;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
///class to store and manipulate Calculator values
public class Calculator extends JFrame implements ActionListener {
	private JPanel[] row = new JPanel[5]; ///<member to hold rows
	private JButton[] button = new JButton[19]; ///<member to hold buttons
	private static final String[] buttonString = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "/", "C", "√", "+/-", "=", "0"}; ///<member to hold button strings
	private JTextArea display = new JTextArea(1,20); ///<member to hold text area for output
	private Font font = new Font("Times new Roman", Font.BOLD, 14); ///<member to hold font
	private boolean[] function = new boolean[4]; ///<member to hold function states
	private double[] temporary = {0, 0}; ///<member to hold temprary values
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
		GridLayout grid = new GridLayout(5,5);
		setLayout(grid);
		for(int i = 0; i < 4; i++)
			function[i] = false;
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
		for(int i = 0; i < 5; i++)
			row[i] = new JPanel();
		row[0].setLayout(f1);
		for(int i = 1; i < 5; i++)
			row[i].setLayout(f2);
		for(int i = 0; i < 19; i++) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(new Dimension(300, 35));
		for(int i = 0; i < 14; i++)
			button[i].setPreferredSize(new Dimension(45, 40));
		for(int i = 14; i < 18; i++)
			button[i].setPreferredSize(new Dimension(100, 40));
		button[18].setPreferredSize(new Dimension(90, 40));
		row[0].add(display);
		add(row[0]);
		for(int i = 0; i < 4; i++)
			row[1].add(button[i]);
		row[1].add(button[14]);
		add(row[1]);
		for(int i = 4; i < 8; i++)
			row[2].add(button[i]);
		row[2].add(button[15]);
		add(row[2]);
		for(int i = 8; i < 12; i++)
			row[3].add(button[i]);
		row[3].add(button[16]);
		add(row[3]);
		row[4].add(button[18]);
		for(int i = 12; i < 14; i++)
			row[4].add(button[i]);
		row[4].add(button[17]);
		add(row[4]);
		pack();
		setVisible(true);
	}
	///member function to clear output state
	private void clear() {
		try {
			display.setText("");
			for(int i = 0; i < 4; i++)
				function[i] = false;
			for(int i = 0; i < 2; i++)
				temporary[i] = 0;
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	///member function to calculate the square root
	private void root() {
		try {
			double value = Math.sqrt(Double.parseDouble(display.getText()));
			display.setText(Double.toString(value));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	///member function to negate the argument
	private void negate() {
		try {
			double value = Double.parseDouble(display.getText());
			if(value != 0) {
				value *= -1;
				display.setText(Double.toString(value));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	///member function to calculate the result
	private void result() {
		try {
			double result = 0;
			temporary[1] = Double.parseDouble(display.getText());
			if(function[2] == true)
				result = temporary[0] * temporary[1];
			else if(function[3] == true)
				result = temporary[0] / temporary[1];
			else if(function[0] == true)
				result = temporary[0] + temporary[1];
			else if(function[1] == true)
				result = temporary[0] - temporary[1];
			display.setText(Double.toString(result));
			for(int i = 0; i < 4; i++)
				function[i] = false;
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	///member function to implement ActionListener
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == button[0])
			display.append("7");
		if(ae.getSource() == button[1])
			display.append("8");
		if(ae.getSource() == button[2])
			display.append("9");
		if(ae.getSource() == button[3]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[0] = true;
			display.setText("");
		}
		if(ae.getSource() == button[4])
			display.append("4");
		if(ae.getSource() == button[5])
			display.append("5");
		if(ae.getSource() == button[6])
			display.append("6");
		if(ae.getSource() == button[7]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[1] = true;
			display.setText("");
		}
		if(ae.getSource() == button[8])
			display.append("1");
		if(ae.getSource() == button[9])
			display.append("2");
		if(ae.getSource() == button[10])
			display.append("3");
		if(ae.getSource() == button[11]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		if(ae.getSource() == button[12])
			display.append(".");
		if(ae.getSource() == button[13]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[3] = true;
			display.setText("");
		}
		if(ae.getSource() == button[14])
			clear();
		if(ae.getSource() == button[15])
			root();
		if(ae.getSource() == button[16])
			negate();
		if(ae.getSource() == button[17])
			result();
		if(ae.getSource() == button[18])
			display.append("0");
	}
}
