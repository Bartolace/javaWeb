package br.com.alura.javawebscreenmatch;

import br.com.alura.javawebscreenmatch.exerciciosExtras.service.ExerciciosStreams;
import br.com.alura.javawebscreenmatch.exerciciosExtras.service.ExerciciosStreams02;
import br.com.alura.javawebscreenmatch.principal.Principal;
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
//		Principal principal = new Principal();
//		principal.exibeMenu();

		ExerciciosStreams02 exe = new ExerciciosStreams02();
		exe.exeComProdutos();
	}
}
