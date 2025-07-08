package br.com.javawebfipemactch;

import br.com.javawebfipemactch.principal.PrincipalFipe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavawebfipemacthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavawebfipemacthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PrincipalFipe principal = new PrincipalFipe();

		principal.exibeMenu();
	}
}
