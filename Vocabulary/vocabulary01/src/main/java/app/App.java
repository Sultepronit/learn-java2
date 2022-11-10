package app;

import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingUtilities;

import app.controllers.Controller;
import app.database.Database;
import app.model.WordCard;

public class App {
	
	/*public static String line = "";
	public static Map<String, List<String>> map2 = null;*/
	
	public static List<WordCard> cardList; 
	
	public static void main(String[] args) {
		System.out.println("Starting!");
		
		try {
			cardList = Database.getCardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Quiz.start();
		
		SwingUtilities.invokeLater(Controller::new);
		
		//MainFrame.returnSplitPane();
	}

}
