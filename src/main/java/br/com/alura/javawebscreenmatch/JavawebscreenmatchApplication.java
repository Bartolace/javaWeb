package br.com.alura.javawebscreenmatch;

import br.com.alura.javawebscreenmatch.model.DadosSerie;
import br.com.alura.javawebscreenmatch.service.ConsumoApi;
import br.com.alura.javawebscreenmatch.service.ConverteDados;
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
		var consumoApi = new ConsumoApi();

		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		var conversor = new ConverteDados();
		DadosSerie dados = conversor.converter(json, DadosSerie.class);
		System.out.println(dados);

	}
}
