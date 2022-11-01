package app.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JSplitPane splitPane;
	
	public MainFrame(JPanel upperPanel, JPanel lowerPanel) {
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		//splitPane.setResizeWeight(0.3);
		
		/*add(splitPane);
		splitPane.setVisible(true);
		
		var quizPanel = new JPanel();
		add(quizPanel);
		quizPanel.setVisible(false);
		splitPane.setVisible(true);
		add(splitPane);*/
		getContentPane().add(splitPane);
		getContentPane().removeAll();
		var quizPanel = new QuizPanel();
		getContentPane().add(quizPanel);
		
		//setSize(1000, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	
}
