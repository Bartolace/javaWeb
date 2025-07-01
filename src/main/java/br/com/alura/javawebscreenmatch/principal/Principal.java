package br.com.alura.javawebscreenmatch.principal;

import br.com.alura.javawebscreenmatch.model.DadosSerie;
import br.com.alura.javawebscreenmatch.model.DadosTemporada;
import br.com.alura.javawebscreenmatch.model.Episodio;
import br.com.alura.javawebscreenmatch.service.ConsumoApi;
import br.com.alura.javawebscreenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

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

		DadosSerie dados = conversor.converter(json, DadosSerie.class);
        System.out.println(dados);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i = 1; i <= dados.totalTemporadas(); i++ ){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);

			DadosTemporada dadosTemporada = conversor.converter(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

//		temporadas.forEach(t ->t.episodios().forEach(e -> System.out.println(e.titulo())));


//        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toList());
//
//        System.out.println("\nTop 10 episodeos por avaliação:");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(s -> System.out.println(s))
//                .limit(10)
//                .map(e -> e.titulo().toUpperCase())
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

//        System.out.println("Digite o trecho do título do episodio: ");
//        var trechoTitulo = leitura.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//        if(episodioBuscado.isPresent()) {
//            System.out.println("Episodio encontrado!");
//            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//        }else {
//            System.out.println("Nenhum episódio encontrado com o título: " + trechoTitulo);
//        }


//        System.out.println("A partir de qual ano deseja ver os Episódios? ");
//        var ano = leitura.nextLine();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(Integer.parseInt(ano), 1, 1);
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .sorted(Comparator.comparing(Episodio::getDataLancamento))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporadas() +
//                                " Episódio: " + e.getNumeroEpisodio() +
//                                " Data lançamento: " + e.getDataLancamento().format(formatador)
//                ));

        Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                         Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacaoPorTemporada);

    }
}
