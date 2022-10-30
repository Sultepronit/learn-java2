package app;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.google.gson.Gson;

import app.controllers.Controller;

public class App {
	
	public static String line = "";
	public static Map<String, List<String>> map2 = null;
	
	public static void main(String[] args) {
		System.out.println("Hello there");
		
		/*try {
			Gson gson = new Gson();
			Reader json = Files.newBufferedReader(Paths.get("data.json"));
			Map<String, String> map = gson.fromJson(json, Map.class);
			var url = map.get("fumbling");
			System.out.println(url);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}*/
		
		//HashMap map2 = null;
		
		try {
			Gson gson = new Gson();
			//Reader json = Files.newBufferedReader(Paths.get("/home/step/Downloads/ultimate.json"));
			//Reader json = Files.newBufferedReader(Paths.get("db/ultimate.json"));
			//Reader json = Files.newBufferedReader(Paths.get("json/ultimate.json"));
			Reader json = Files.newBufferedReader(Paths.get("ultimate.json"));
			//Map map2 = gson.fromJson(json, Map.class);
			map2 = (Map<String, List<String>>) gson.fromJson(json, Map.class);
			List<String> urls = (List<String>) map2.get("ukraine");
			
			//System.out.println(url.getClass());
			System.out.println(urls);
			System.out.println(urls.size());
			line = urls.get(0);
			//System.out.println(line);
			//String[] array = urls.toArray(new String[0]);
			//var player = new PlayMP3();
			//PlayMP3.play(array);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		
		/*try {
			Db0.testDb();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			Dict0.testDb();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SwingUtilities.invokeLater(Controller::new);
	}

}
