package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static String dbUrl = "jdbc:sqlite:vocab-dic.db";
	private static Connection conn;
	private static Statement stmt; 
	
	public static String getUrlsList(String word) throws ClassNotFoundException, SQLException {
		//Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(dbUrl);
		// if no error -- everithing works
		System.out.println(conn);
		stmt = conn.createStatement();
		
		var sql = "select * from dic where word='" + word + "'";
		var rs = stmt.executeQuery(sql);
		String urls = rs.getString("urls");
		System.out.println(urls);
		
		stmt.close();
		conn.close();
		
		return urls;
	}
}
