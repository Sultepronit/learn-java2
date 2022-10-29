package app;

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
	
	public static void main(String[] args) {
		System.out.println("Hello there");
		
		try {
			Gson gson = new Gson();
			Reader json = Files.newBufferedReader(Paths.get("data.json"));
			Map<String, String> map = gson.fromJson(json, Map.class);
			var url = map.get("fumbling");
			System.out.println(url);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		
		try {
			Gson gson = new Gson();
			Reader json = Files.newBufferedReader(Paths.get("/home/step/Downloads/ultimate.json"));
			Map map2 = gson.fromJson(json, Map.class);
			List<String> url = (List<String>) map2.get("fumbling");
			//System.out.println(url.getClass());
			System.out.println(url);
			line = url.get(0);
			System.out.println(line);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		
		SwingUtilities.invokeLater(Controller::new);
	}

}
