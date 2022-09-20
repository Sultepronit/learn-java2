package application;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//System.out.println("Hello!");
		//Class.forName("org.sqlite.JDBC");
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//String dbUrl = "jdbc:sqlite:people.db";
		String dbUrl = "jdbc:mysql://localhost:3306/people";
		
		var conn = DriverManager.getConnection(dbUrl, "root", "password");
		
		// if no error --g everithing works
		//System.out.println(conn);
		
		var stmt = conn.createStatement();
		
		//do not commit changes immediately
		// by default conn.setAutoCommit(true);
		conn.setAutoCommit(false);
		
		int[] ids = {1, 2, 3};
		String[] names = {"Ann", "Bob", "Sue"};
		
		var sql = "insert into user (id, name) values(?, ?)";
		var insertStmt = conn.prepareStatement(sql);
		
		for(int i = 0; i < ids.length; i++) {
			insertStmt.setInt(1, ids[i]);
			insertStmt.setString(2, names[i]);
			
			//works only once!
			insertStmt.executeUpdate();
		}
		
		// commit all the changes / needed if conn.setAutoCommit(false);
		conn.commit();
		
		insertStmt.close();
		
		sql = "select id, name from user";
		var rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			System.out.println(id + ": " + name);
		}
		
		stmt.close();
		conn.close();

	}

}
