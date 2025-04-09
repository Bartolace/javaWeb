package br.com.alura.javawebscreenmatch.principal;

import br.com.alura.javawebscreenmatch.model.DadosSerie;
import br.com.alura.javawebscreenmatch.model.DadosTemporada;
import br.com.alura.javawebscreenmatch.service.ConsumoApi;
import br.com.alura.javawebscreenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.printf("Digite o nome da série para buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        var dadosSerie = conversor.converter(json, DadosSerie.class);
        System.out.printf(dadosSerie.toString() + "\n");

		List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i = 1; i <= dadosSerie.totalTemporadas(); i++ ){
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.converter(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(t ->t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
