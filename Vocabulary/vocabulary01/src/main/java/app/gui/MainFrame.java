package app.gui;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//private static JSplitPane splitPane;
	//private QuizPanel quizPanel = new QuizPanel();
	//private static QuizPanel quizPanel = null;
	
	public MainFrame(JPanel addCard, JPanel quizPanel) {
		//splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		//splitPane.setResizeWeight(0.3);
		//quizPanel = new QuizPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane);
		
		tabbedPane.addTab("Cards", addCard);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		tabbedPane.addTab("Quiz", quizPanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		//setSize(1000, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
