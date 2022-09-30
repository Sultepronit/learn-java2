package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import greeters.Greeter;

@Configuration
public class Config {
	
	@Bean
	public Greeter configureGreeter() {
		System.out.println("Here we are!");
		return new Greeter();
	}

}
