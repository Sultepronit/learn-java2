package application;

import javax.swing.SwingUtilities;

import gui.MainFrame;
import gui.WordsPanel;
import gui.WordsPanel3;

class Controller {
	private static MainFrame mainFrame;
	private static WordsPanel wordsPanel;
	private static WordsPanel3 wordsPanel3;
	
	public Controller() {
		mainFrame = new MainFrame();
		wordsPanel = new WordsPanel();
		wordsPanel3 = new WordsPanel3();
		
		mainFrame.setContentPane(wordsPanel);
	}
}

public class App {

	public static void main(String[] args) {
		
		//Controller controller = new Controller();
		//SwingUtilities.invokeLater(controller);
		SwingUtilities.invokeLater(Controller::new);

	}

}
