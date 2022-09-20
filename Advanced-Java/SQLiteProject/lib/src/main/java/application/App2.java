package application;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello!");
		Class.forName("org.sqlite.JDBC");
		
		String dbUrl = "jdbc:sqlite:people.db";
		var conn = DriverManager.getConnection(dbUrl);
		
		// if no error -- everithing works
		//System.out.println(conn);
		
		var stmt = conn.createStatement();
		
		//do not commit changes immediately
		// by default conn.setAutoCommit(true);
		conn.setAutoCommit(false);
		
		//var sql = "create table user (id integer primary key, name text not null)";
		var sql = "create table if not exists user (id integer primary key, name text not null)";
		stmt.execute(sql);
		
		//not effective
		/*sql = "insert into user (id, name) values (0, 'Bob')";
		stmt.execute(sql);
		sql = "insert into user (id, name) values (1, 'Mary')";
		stmt.execute(sql);*/
		int[] ids = {1, 2, 3};
		String[] names = {"Ann", "Bob", "Sue"};
		
		sql = "insert into user (id, name) values(?, ?)";
		var insertStmt = conn.prepareStatement(sql);
		
		for(int i = 0; i < ids.length; i++) {
			insertStmt.setInt(1, ids[i]);
			insertStmt.setString(2, names[i]);
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
		
		// now, we deleted our table
		sql = "drop table user";
		stmt.execute(sql);
		//needed if conn.setAutoCommit(false);
		conn.commit();
		
		stmt.close();
		conn.close();

	}

}
