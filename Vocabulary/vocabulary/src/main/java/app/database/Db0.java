package app.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Db0 {
	
	public static int re = 0;
	
	public static void testDb() throws ClassNotFoundException, SQLException {
	Class.forName("org.sqlite.JDBC");
	
	String dbUrl = "jdbc:sqlite:test.db";
	var conn = DriverManager.getConnection(dbUrl);
	
	// if no error -- everithing works
	System.out.println(conn);
	
	var stmt = conn.createStatement();
	
	//do not commit changes immediately
	// by default conn.setAutoCommit(true);
	//conn.setAutoCommit(false);
	
	var sql = "create table if not exists test (id integer primary key autoincrement, name text not null)";
	stmt.execute(sql);
	
	//not effective
	/*sql = "insert into user (id, name) values (0, 'Bob')";
	stmt.execute(sql);
	sql = "insert into user (id, name) values (1, 'Mary')";
	stmt.execute(sql);*/
	int[] ids = {1, 2, 3};
	String[] names = {"Ann", "Bob", "Sue"};
	
	//sql = "insert into user (id, name) values(?, ?)";
	sql = "insert into test (name) values(?)";
	var insertStmt = conn.prepareStatement(sql);
	
	insertStmt.setString(1, "boo");
	insertStmt.executeUpdate();
	
	/*for(int i = 0; i < ids.length; i++) {
		insertStmt.setInt(1, ids[i]);
		insertStmt.setString(2, names[i]);
		insertStmt.executeUpdate();
	}*/
	insertStmt.close();
	
	sql = "select id, name from test";
	var rs = stmt.executeQuery(sql);
	
	while(rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		
		re = id;
		
		System.out.println(id + ": " + name);
	}
	
	stmt.close();
	conn.close();
	
	}
}
