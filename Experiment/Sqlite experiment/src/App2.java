import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("Start");
		
		Class.forName("org.sqlite.JDBC");
		
		String dbUrl = "jdbc:sqlite:new.db";
		
		Connection conn = DriverManager.getConnection(dbUrl);
		System.out.println(conn);
		
		Statement stmt = conn.createStatement();
		System.out.println(stmt);
		
		//conn.setAutoCommit(false);
		
		var sql = "create table if not exists test1 (id integer primary key, name text not null)";
		
		stmt.execute(sql);
	}	

}
