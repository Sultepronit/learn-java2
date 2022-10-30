package app;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class App {
	
	public static Map<String, List<String>> map2 = null;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		System.out.println("Hello there");
		
		Class.forName("org.sqlite.JDBC");
		
		String dbUrl = "jdbc:sqlite:test.db";
		var conn = DriverManager.getConnection(dbUrl);
		
		// if no error -- everithing works
		System.out.println(conn);
		
		var stmt = conn.createStatement();
		
		var sql = "drop table dic";
		stmt.execute(sql);
		
		//var sql = "create table if not exists dic (word text primary key, urls text not null)";
		sql = "create table if not exists dic (word text primary key, urls text not null)";
		stmt.execute(sql);
		
		sql = "insert into dic (word, urls) values(?, ?)";
		var insertStmt = conn.prepareStatement(sql);
		
		Gson gson = new Gson();
		Reader json = Files.newBufferedReader(Paths.get("ultimate.json"));
		map2 = (Map<String, List<String>>) gson.fromJson(json, Map.class);

		//String[] array = urls.toArray(new String[0]);

		int i = 0;
		for(var entry: map2.entrySet()) {
			/*i++;
			if(i > 1000) break;*/
			System.out.println(entry.getKey());
			var urls = entry.getValue();
			var builder = new StringBuilder();
			for(int j = 0; j < urls.size(); j++) {
				//System.out.println(urls.get(j));
				builder.append(urls.get(j));
				if(j + 1 == urls.size()) break;
				builder.append("+++");
			}
			//System.out.println(builder);
			
			insertStmt.setString(1, entry.getKey());
			insertStmt.setString(2, builder.toString());
			insertStmt.executeUpdate();
		}
		insertStmt.close();
		
		sql = "SELECT * FROM dic WHERE word='ukraine'";
		var rs = stmt.executeQuery(sql);
		System.out.println(rs.getString("urls"));
		
	}

}
