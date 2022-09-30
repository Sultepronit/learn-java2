package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"greeter", "main"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

}