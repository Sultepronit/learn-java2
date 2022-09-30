package greeters;

import org.springframework.stereotype.Component;

@Component
public class Greeter2 {
	public void greet() {
		System.out.println("Hello! I am a bean.");
	}
}
