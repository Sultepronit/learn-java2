package app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import app.model.Book;

public class BookService {
	private static final String DATA_URL = "http://localhost:8080/books";
	
	public List<Book> getAll() throws IOException {
		var url = new URL(DATA_URL);
		var conn = (HttpURLConnection)url.openConnection();
		conn.setDoInput(true);
		conn.setRequestProperty("Accept", "application/json,text/plain");
		conn.setRequestMethod("GET");
		
		conn.connect();
		var builder = new StringBuilder();
		try(var br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line);
			}
		}
		conn.disconnect();
	
		//System.out.println(builder.toString());
		Gson gson = new Gson();
		Book[] bookArray = gson.fromJson(builder.toString(), Book[].class);
		
		return Arrays.asList(bookArray);
	}
}
