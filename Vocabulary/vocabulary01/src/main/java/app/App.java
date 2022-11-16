package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	/*public static String line = "";
	public static Map<String, List<String>> map2 = null;*/
	
	//public static List<WordCard> cardList; 
	
	public static void main(String[] args) {
		System.out.println("Starting!");
		
		/*try {
			cardList = Database.getCardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		//Quiz.start();
		
		SwingUtilities.invokeLater(Controller::new);
		
		//MainFrame.returnSplitPane();
	}

}
