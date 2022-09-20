package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
	
	private Connection conn;
	private List<User> users;
	
	private static int NUM_TEST_USERS = 4;
	
	private List<User> loadUsers() throws IOException {
		return Files
				.lines(Paths.get("../../greatexpectations.txt"))//lines
				.map(line -> line.split("[^A-Za-z]"))//arrays of words
				.map(Arrays::asList)//Lists of words
				.flatMap(list -> list.stream())//words and empty entries
				.filter(word -> word.length() > 3 && word.length() < 20)
				.map(word -> new User(word, "pass" + word))
				.limit(NUM_TEST_USERS)
				.collect(Collectors.toList());
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		
		users = loadUsers();
		
		//System.out.println(users);
		//System.out.println(users.size());
		
		var props = Profile.getProperties("db"); 
		
		var db = Database.instance();
		
		db.connect(props);
		
		conn = db.getConnection();
		
		// changes will be actual in a session, but won't be saved 
		conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		System.out.println("After");
		Database.instance().close();
	}
	
	private int getMaxId() throws SQLException {
		var stmt = conn.createStatement();
		
		var rs = stmt.executeQuery("select max(id) as id from user");
		rs.next();
		var id = rs.getInt("id");
		
		stmt.close();
		return id;
	}
	
	private List<User> getUsersInRange(int minId, int maxId) throws SQLException {
		
		var result = new ArrayList<User>();
		
		var stmt = conn.prepareStatement("select id, name, password from user where id >= ? and id <= ?");
		stmt.setInt(1, minId);
		stmt.setInt(2, maxId);
		
		var rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String password = rs.getString("password");
			
			var user = new User(id, name, password);
			result.add(user);
		}
		
		
		stmt.close();
		
		return result;
	}
	
	@Test
	public void testFindAndUpdate() throws SQLException {
		var user = users.get(0);// user with ID=0 
		//System.out.println(user);
		UserDao userDao = new UserDaoImpl();
		
		userDao.save(user);// saving user in a database which automatically gives it last ID 
		
		var maxId = getMaxId();// getting an ID of our last added user
		//System.out.println(maxId);
		user.setId(maxId); //now user in our app has the same Id as in database 
		//System.out.println(user);
		
		var retrievedUserOpt = userDao.findById(maxId);
		assertTrue("no user retrieved", retrievedUserOpt.isPresent());
		var retrievedUser = retrievedUserOpt.get();
		//System.out.println(retrievedUser);
		assertEquals("retrieved user doesn't match saved user", user, retrievedUser);
		
		user.setName("abcd");
		user.setPassword("passsss");
		userDao.update(user);
		
		retrievedUserOpt = userDao.findById(maxId);
		assertTrue("no user retrieved after update  ", retrievedUserOpt.isPresent());
		retrievedUser = retrievedUserOpt.get();
		System.out.println(retrievedUser);
		assertEquals("retrieved user doesn't match updated user", user, retrievedUser);
	}
	
	@Test
	public void testSaveMultiple() throws SQLException {
		UserDao userDao = new UserDaoImpl();
		
		for(var u: users) {
			userDao.save(u);
		}
		
		var maxId = getMaxId();
		//System.out.println(maxId);
		
		for(int i = 0; i < users.size(); i++) {
			int id = maxId - users.size() + 1 + i;
			users.get(i).setId(id);
		}
		
		var retrievedUsers = getUsersInRange(maxId - users.size() + 1, maxId);
		
		assertEquals("inapropriate size!", retrievedUsers.size(), NUM_TEST_USERS);
		assertEquals("users are not the same!", users, retrievedUsers);
	}
	
	@Test
	public void testDelete() throws SQLException {
		UserDao userDao = new UserDaoImpl();
		
		for(var u: users) {
			userDao.save(u);
		}
		
		var maxId = getMaxId();
		//System.out.println(maxId);
		
		for(int i = 0; i < users.size(); i++) {
			int id = maxId - users.size() + 1 + i;
			users.get(i).setId(id);
		}
		
		var deleteUserIndex = NUM_TEST_USERS / 2;
		var deleteUser = users.get(deleteUserIndex);
		users.remove(deleteUser);
		System.out.println(deleteUser);
		System.out.println(users);
		userDao.delete(deleteUser);
		
		
		var retrievedUsers = getUsersInRange(maxId - NUM_TEST_USERS + 1, maxId);
		System.out.println(retrievedUsers);
		
		assertEquals("inapropriate size!", retrievedUsers.size(), users.size());
		assertEquals("users are not the same!", users, retrievedUsers);
	}
	
	@Test
	public void testGetAll() throws SQLException {
		UserDao userDao = new UserDaoImpl();
		
		for(var u: users) {
			userDao.save(u);
		}
		
		var maxId = getMaxId();
		//System.out.println(maxId);
		
		for(int i = 0; i < users.size(); i++) {
			int id = maxId - users.size() + 1 + i;
			users.get(i).setId(id);
		}
		
		var dbUsers = userDao.getAll();
		dbUsers = dbUsers.subList(dbUsers.size() - users.size(), dbUsers.size());		
		
		assertEquals("inapropriate size!", dbUsers.size(), NUM_TEST_USERS);
		assertEquals("users are not the same!", users, dbUsers);
	}
	
	@Test
	public void testSave() throws SQLException {
		User user = new User("Jupiter", "passs");
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		var stmt = conn.createStatement();
		var rs = stmt.executeQuery("select id, name, password from user order by id desc");
		var result = rs.next();
		assertTrue("cannot retrieve inserted user", result);
		
		var name = rs.getString("name");
		assertEquals("user name doesn't match retrieved", user.getName(), name);
		
		var password = rs.getString("password");
		assertEquals("user password doesn't match retrieved", user.getPassword(), password);
		
		stmt.close();
	}
}
