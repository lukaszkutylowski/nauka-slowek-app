package pl.lukaszkutylowski;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class NaukaSlowekAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(NaukaSlowekAppApplication.class, args);
		val controller = ctx.getBean(LinguController.class);
		controller.mainLoop();
	}

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
