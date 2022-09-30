package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"greeter", "main"})
//@SpringBootApplication(scanBasePackages = {"configuration", "main"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

}
