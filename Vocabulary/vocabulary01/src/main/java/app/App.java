package app;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import app.controllers.Controller;
import app.database.Database;
import app.database.Dict0;

public class App {
	
	public static String line = "";
	public static Map<String, List<String>> map2 = null;
	
	public static void main(String[] args) {
		System.out.println("Hello there");
		
		Quiz.start();
		
		/*try {
			Database.updateStats();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SwingUtilities.invokeLater(Controller::new);
	}

}
