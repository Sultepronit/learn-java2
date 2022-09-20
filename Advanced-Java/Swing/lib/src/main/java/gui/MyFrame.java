package gui;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MyFrame() {
		super("Little window");
		
		setSize(400, 200);
		//setDefaultCloseOperation(HIDE_ON_CLOSE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
