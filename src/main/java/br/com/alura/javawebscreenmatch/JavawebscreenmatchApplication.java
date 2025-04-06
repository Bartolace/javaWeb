package br.com.alura.javawebscreenmatch;

import br.com.alura.javawebscreenmatch.model.DadosEpisodio;
import br.com.alura.javawebscreenmatch.model.DadosSerie;
import br.com.alura.javawebscreenmatch.model.DadosTemporada;
import br.com.alura.javawebscreenmatch.principal.Principal;
import br.com.alura.javawebscreenmatch.service.ConsumoApi;
import br.com.alura.javawebscreenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavawebscreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavawebscreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		var consumoApi = new ConsumoApi();
//
//		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
//		var conversor = new ConverteDados();
//		DadosSerie dados = conversor.converter(json, DadosSerie.class);
//
//
//		json = consumoApi.obterDados("https://omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
//		DadosEpisodio dadosEpisodio = conversor.converter(json, DadosEpisodio.class);
//
//		List<DadosTemporada> temporadas = new ArrayList<>();
//
//		for(int i = 1; i <= dados.totalTemporadas(); i++ ){
//			json = consumoApi.obterDados("https://omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
//			DadosTemporada dadosTemporada = conversor.converter(json, DadosTemporada.class);
//			temporadas.add(dadosTemporada);
//		}
//
//		temporadas.forEach(System.out::println);
		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
