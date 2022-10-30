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
		
		String urls = null;
		try {
			//urls = Dict0.testDb();
			urls = Database.getUrlsList("some");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var array = urls.split("\\+++");
		/*System.out.println(array[0]);
		System.out.println(array[1]);*/
		
		//PlayMP3.play(array);
		
		SwingUtilities.invokeLater(Controller::new);
	}

}
