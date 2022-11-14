package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.model.WordCard;

public class Database {
	
	private static String dbUrl = "jdbc:sqlite:vocab-dic.db";
	private static Connection conn;
	private static Statement stmt;
	
	public static String getUrlsList(String word) throws ClassNotFoundException, SQLException {
		/*Connection conn;
		Statement stmt;*/
		//Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(dbUrl);
		// if no error -- everithing works
		System.out.println(conn);
		stmt = conn.createStatement();
		
		var sql = "select * from dic where word='" + word + "'";
		var rs = stmt.executeQuery(sql);
		//System.out.println(rs);
		String urls = "---";
		//String urls = rs.getString("urls");
		while(rs.next()) {
			urls = rs.getString("urls");
		}
		System.out.println(urls);
		
		stmt.close();
		conn.close();
		
		return urls;
	}
	
	public static void saveCard(WordCard card) throws SQLException {
		/*Connection conn;
		Statement stmt;*/
		conn = DriverManager.getConnection(dbUrl);
		System.out.println(conn);
		stmt = conn.createStatement();
		String sql = null;
		
		/*sql = "DROP TABLE vocab";
		stmt.execute(sql);
		
		var cr1 = "CREATE TABLE IF NOT EXISTS vocab (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
		var cr2 = "word TEXT, transc TEXT, transl TEXT, example TEXT, mp3urls TEXT, ";
		var cr3 = "status INTEGER, forward INTEGER, backward INTEGER)";
		stmt.execute(cr1 + cr2 + cr3);*/
		
		sql = "INSERT INTO vocab (word, transc, transl, example, mp3urls, status, forward, backward) values(?,?,?,?,?, 0, 0, 0)";
		var insertStmt = conn.prepareStatement(sql);
		insertStmt.setString(1, card.getWord());
		insertStmt.setString(2, card.getTransc());
		insertStmt.setString(3, card.getTransl());
		insertStmt.setString(4, card.getExample());
		insertStmt.setString(5, card.getMp3String());
		insertStmt.executeUpdate();
		insertStmt.close();
		
		sql = "SELECT id, word, transl FROM vocab";
		var rs = stmt.executeQuery(sql);
		while(rs.next()) {
			int id = rs.getInt("id");
			String word = rs.getString("word");
			String transl = rs.getString("transl");
			System.out.println(id + ": " + word + ": " + transl);
		}
		System.out.println("Done!");
		stmt.close();
		conn.close();
	}
	
	public static void updateStats(WordCard card) throws SQLException {
		conn = DriverManager.getConnection(dbUrl);
		stmt = conn.createStatement();
		
		var sql = "UPDATE vocab SET status=?, forward=?, backward=? WHERE id=?";
		var updateStmt = conn.prepareStatement(sql);
		//int id = 3, status = 2, forward = 1, backward = -1;
		updateStmt.setInt(1, card.getStatus());
		updateStmt.setInt(2, card.getForward());
		updateStmt.setInt(3, card.getBackward());
		updateStmt.setInt(4, card.getId());
		updateStmt.executeUpdate();
		updateStmt.close();
		
		System.out.println("Updated!");
		stmt.close();
		conn.close();
	}
	
	public static ArrayList<WordCard> getCardList() throws SQLException {
		ArrayList<WordCard> list = new ArrayList<>();
		
		conn = DriverManager.getConnection(dbUrl);
		stmt = conn.createStatement();
		
		var sql = "SELECT * FROM vocab";
		var rs = stmt.executeQuery(sql);
		while(rs.next()) {
			int id = rs.getInt("id");
			String word =rs.getString("word");
			String transc =rs.getString("transc");
			String transl =rs.getString("transl");
			String example =rs.getString("example");
			String mp3urls =rs.getString("mp3urls");
			int status = rs.getInt("status");
			int forward = rs.getInt("forward");
			int backward = rs.getInt("backward");
			//System.out.println(id + ": " + word + ": " + transl);
			var card = new WordCard(id, word, transc, transl, example, mp3urls, status, forward, backward);
			System.out.println(card);
			list.add(card);
		}
		//System.out.println(list);
			
		stmt.close();
		conn.close();
		return list;
	}
}
