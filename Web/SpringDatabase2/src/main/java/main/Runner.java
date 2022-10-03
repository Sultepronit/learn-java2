package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import entities.User;
import repositories.UserDao;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Saving...");
		var user1 = new User("Mihal", "mihal@com.ua");
		userDao.save(user1);
		
	}
	
}
