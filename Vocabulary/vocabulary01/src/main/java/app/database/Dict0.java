package app.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import app.App;

public class Dict0 {
	
	public static int re = 0;
	
	public static String testDb() throws ClassNotFoundException, SQLException {
		//Class.forName("org.sqlite.JDBC");
		
		//String dbUrl = "jdbc:sqlite:test.db";
		//String dbUrl = "jdbc:sqlite:resourses/test.db";
		String dbUrl = "jdbc:sqlite:vocab-dic.db";
		var conn = DriverManager.getConnection(dbUrl);
		
		// if no error -- everithing works
		System.out.println(conn);
		
		var stmt = conn.createStatement();
		
		var sql = "select * from dic where word='ukrainian'";
		var rs = stmt.executeQuery(sql);
		String urls = rs.getString("urls");
		System.out.println(urls);
		
		stmt.close();
		conn.close();
		
		return urls;
	
	}
}
