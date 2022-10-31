package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import app.model.WordCard;

public class Database {
	
	private static String dbUrl = "jdbc:sqlite:vocab-dic.db";
	/*private static Connection conn;
	private static Statement stmt;*/ 
	
	public static String getUrlsList(String word) throws ClassNotFoundException, SQLException {
		Connection conn;
		Statement stmt;
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
	
	public static void saveCard(WordCard card) throws SQLException {
		Connection conn;
		Statement stmt;
		conn = DriverManager.getConnection(dbUrl);
		System.out.println(conn);
		stmt = conn.createStatement();
		
		/*var cr1 = "CREATE TABLE IF NOT EXISTS vocab (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
		var cr2 = "word TEXT, transc TEXT, transl TEXT, example TEXT, mp3urls TEXT)";
		stmt.execute(cr1 + cr2);*/
		
		var sql = "INSERT INTO vocab (word, transc, transl, example, mp3urls) values(?,?,?,?,?)";
		var insertStmt = conn.prepareStatement(sql);
		insertStmt.setString(1, card.getWord());
		insertStmt.setString(2, card.getTransc());
		insertStmt.setString(3, card.getTransl());
		insertStmt.setString(4, card.getExample());
		insertStmt.setString(5, card.getMp3String());
		insertStmt.executeUpdate();
		insertStmt.close();
		
		//String sql = null;
		sql = "SELECT id, word, transl FROM vocab";
		var rs = stmt.executeQuery(sql);
		System.out.println(rs);
		String word = rs.getString("word");
		System.out.println(word);
		/*while(rs.next()) {
			int id = rs.getInt("id");
			String word = rs.getString("word");
			String transl = rs.getString("transl");
			System.out.println(id + ": " + word + ": " + transl);
		}*/
		System.out.println("Done!");
		stmt.close();
		conn.close();
	}
}
