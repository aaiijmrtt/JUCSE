import javax.swing.JFrame;

public class Display extends JFrame {

	public Display(Grid grid) {
		this(grid, "Display");
	}

	public Display(Grid grid, String head) {
		super(head);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(grid);
		pack();
		setVisible(true);
	}

}
