package br.com.alura.javawebscreenmatch.exerciciosExtras.service;

import br.com.alura.javawebscreenmatch.exerciciosExtras.model.Pessoa;
import br.com.alura.javawebscreenmatch.exerciciosExtras.model.Produto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExerciciosStreams02 {

    public void getMaiorNumero(){
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        IntSummaryStatistics est = numeros.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println(est.getMax());
    }

    public void agrupaPorTamanho() {
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
        Map<Integer, List<String>> agrupamento = palavras.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.groupingBy(String::length,
                        Collectors.toList()));

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
        Stream<Integer> result = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n);

        result.forEach(System.out::println);
    }

    public void separaParImpar() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        numeros.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


    public void exeComProdutos() {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );


        Map<String, List<Produto>> agrupados = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.toList()));

        agrupados.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().size()
                ));
    }






}
