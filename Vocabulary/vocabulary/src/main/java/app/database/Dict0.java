package app.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import app.App;

public class Dict0 {
	
	public static int re = 0;
	
	public static void testDb() throws ClassNotFoundException, SQLException {
	Class.forName("org.sqlite.JDBC");
	
	//String dbUrl = "jdbc:sqlite:db/test2.db";
	String dbUrl = "jdbc:sqlite:dic0.db";
	var conn = DriverManager.getConnection(dbUrl);
	
	// if no error -- everithing works
	System.out.println(conn);
	
	var stmt = conn.createStatement();
	
	//do not commit changes immediately
	// by default conn.setAutoCommit(true);
	//conn.setAutoCommit(false);
	
	//var sql = "create table if not exists test (id integer primary key autoincrement, name text not null)";
	var sql = "create table if not exists dic0 (word text primary key, url text not null)";
	stmt.execute(sql);
	
	sql = "insert into dic0 (word, url) values(?, ?)";
	var insertStmt = conn.prepareStatement(sql);
	
	/*int i = 0;
	for(var entry: App.map2.entrySet()) {
		i++;
		if(i > 1000) break;
		System.out.println(entry.getKey());
		System.out.println(entry.getValue().get(0));
		insertStmt.setString(1, entry.getKey());
		insertStmt.setString(2, entry.getValue().get(0));
		insertStmt.executeUpdate();
	}*/
	
	insertStmt.close();
	
	sql = "select word, url from dic0";
	var rs = stmt.executeQuery(sql);
	
	while(rs.next()) {
		String word = rs.getString("word");
		String url = rs.getString("url");
		
		//re = id;
		
		System.out.println(word + ": " + url);
	}
	
	stmt.close();
	conn.close();
	
	}
}
