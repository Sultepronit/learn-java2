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
		var user1 = new User("Charles", "charles@com.ua");
		var user0 = userDao.save(user1);
		System.out.println(user0);
		
		userDao.findAll().forEach(u -> System.out.println("Find all: " + u));
		
		var retrievedUserOpt = userDao.findById(1l);
		
		if(retrievedUserOpt.isPresent()) {
			System.out.println("Find by id: " + retrievedUserOpt.get());
		}
	}
	
}
