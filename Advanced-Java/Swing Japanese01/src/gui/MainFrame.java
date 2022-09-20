package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		super("Japanese0");
		
		//setSize(1000,1000);
		//setSize(0,0);
		//pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		System.out.println(getContentPane().getSize());
		
	}
	
}
