package app;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class App {
	
	
	public static void main(String[] args) throws IOException {
		System.out.println("Hello there");
		
		Gson gson = new Gson();
		Reader json = Files.newBufferedReader(Paths.get("ultimate.json"));
		Map map2 = (Map<String, List<String>>) gson.fromJson(json, Map.class);
		List<String> urls = (List<String>) map2.get("ukraine");
		//System.out.println(urls.getClass());
		System.out.println(urls);
		System.out.println(urls.size());

		//String[] array = urls.toArray(new String[0]);

		int i = 0;
		for(var entry: map2.entrySet()) {
			i++;
			if(i > 1000) break;
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().get(0));
			/*insertStmt.setString(1, entry.getKey());
			insertStmt.setString(2, entry.getValue().get(0));
			insertStmt.executeUpdate();*/
		}
		
	}

}
