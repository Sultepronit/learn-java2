package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		super("Swing Demo");
		
		setJMenuBar(createMenu());
		
		setSize(1000, 800); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); //closes app when main window is closed
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE); // closes only a window
		setVisible(true);// shows the window
	}
	
	private JMenuBar createMenu() {
		var menuBar = new JMenuBar();
		
		var fileMenu = new JMenu("File");
		var exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		
		var changeMenu = new JMenu("Change");
		var panel2 = new JMenuItem("Panel2");
		var panel1 = new JMenuItem("Panel1");
		panel2.addActionListener(e -> Controller.changePanel());
		panel1.addActionListener(e -> Controller.changePanel1());
		changeMenu.add(panel2);
		changeMenu.add(panel1);
		menuBar.add(changeMenu);
		
		return menuBar;
	}
}
