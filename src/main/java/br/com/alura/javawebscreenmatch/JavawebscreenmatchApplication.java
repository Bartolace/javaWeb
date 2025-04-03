package br.com.alura.javawebscreenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavawebscreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavawebscreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello word!");
	}
}
