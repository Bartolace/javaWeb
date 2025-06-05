package br.com.alura.javawebscreenmatch;

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

//		json = consumoApi.obterDados("https://omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
//		DadosEpisodio dadosEpisodio = conversor.converter(json, DadosEpisodio.class);
//


		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
