package com.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class App {
	public static void main(String[] args) {
		
		var props = Profile.getProperties("db");
		
		var db = Database.instance();
		// cannot use private constructor 
		//Database db2 = new Database();
		
		try {
			db.connect(props);
		} catch (SQLException e) {
			System.out.println("Cannot connect to database!");
			return;
		}
		
		System.out.println("Connected");
		
		UserDao userDao = new UserDaoImpl();
		
		///// create
		/*userDao.save(new User("Mars"));
		userDao.save(new User("Mercury"));
		System.out.println("Done!");*/
		
		var users = userDao.getAll();
		users.forEach(System.out::println);
		
		var id = 3;
		var userOpt = userDao.findById(id);
		//System.out.println(userOpt);
		// only when not empty!!!
		//System.out.println(userOpt.get());
		if(userOpt.isPresent()) {
			///// retrieve
			User user = userOpt.get();
			System.out.println(user);
			///// update
			user.setName("Snoopy");
			userDao.update(user);
		}
		else System.out.println("No user with id: " + id);
		
		///// delete
		userDao.delete(new User(7, null));
		
		try {
			db.close();
		} catch (SQLException e) {
			System.out.println("Cannot close database connection!");
		}
	}
}
