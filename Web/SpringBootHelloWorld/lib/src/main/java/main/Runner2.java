package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import greeters.Greeter2;

@Component
public class Runner2 implements CommandLineRunner {

	@Autowired
	private Greeter2 greeter; 
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("This bean2 is working!");
		greeter.greet();
		
	}
	
}
