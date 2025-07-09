package br.com.alura.javawebscreenmatch.exerciciosExtras.service;

import br.com.alura.javawebscreenmatch.exerciciosExtras.model.Pessoa;
import br.com.alura.javawebscreenmatch.exerciciosExtras.model.Produto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExerciciosStreams02 {

    public void getMaiorNumero() {
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        Optional<Integer> max = numeros.stream()
                .max(Integer::compare);

        max.ifPresent(System.out::println);
    }

    public void agrupaPorTamanho() {
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
        Map<Integer, List<String>> agrupamento = palavras.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.groupingBy(String::length));
        System.out.println(agrupamento);
    }

    public void concatenandoComVirgula() {
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
        String joinComVirgula = nomes.stream()
                .collect(Collectors.joining(", "));

        System.out.println(joinComVirgula);
    }

    public void somaDoQuadardoDosPares() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer somaDosQuadradosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);

        System.out.println(somaDosQuadradosPares);
    }

    public void separaParImpar() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        Map<Boolean, List<Integer>> particionando = numeros.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println(particionando.get(true));
        System.out.println(particionando.get(false));
    }


    public void mYexeComProdutosAfterResolucao() {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        Map<String, List<Produto>> agrupados = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        Map<String, DoubleSummaryStatistics> est = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.summarizingDouble(Produto::getPreco)
                ));

        Map<String, Long> countByCat = est.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getCount()
                ));

        Map<String, Optional<Produto>> maxPrecoByCarOrOptinal = agrupados.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparingDouble(Produto::getPreco).reversed())
                                .findFirst()
                ));

        Map<String, Double> precoTotalByCat = est.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getSum()
                ));
    }

    public void exeComProdutosResolucao() {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        Map<String, List<Produto>> agrupados = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        Map<String, Long> quantidadePorCat = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));

        Map<String, Optional<Produto>> maxPriceByCat = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco)))
                );

        Map<String, Double> summByCat = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.summingDouble(Produto::getPreco))
                );
    }


}
