package application;

import javax.swing.SwingUtilities;

import Database0.ReadFile;
import Database0.ReadKanjiDB;
import gui.MainFrame;
import gui.WordKanjiPanel;
import gui.WordPanel;

class Controller {
	private static MainFrame mainFrame;
	//public static MainFrame mainFrame;
	private static WordKanjiPanel wordsPanel;
	
	public Controller() {
		mainFrame = new MainFrame();
		wordsPanel = new WordKanjiPanel();
		
		mainFrame.setContentPane(wordsPanel);
	}
}

public class App {

	public static void main(String[] args) {
		
		new ReadFile();
		new ReadKanjiDB();
		//Controller controller = new Controller();
		//SwingUtilities.invokeLater(controller);
		SwingUtilities.invokeLater(Controller::new);
		//System.out.println(Controller.mainFrame.getContentPane().getSize());
		WordPanel.forward();

	}

}
