package br.com.alura.javawebscreenmatch.exerciciosExtras.service;

import br.com.alura.javawebscreenmatch.exerciciosExtras.model.Pessoa;
import br.com.alura.javawebscreenmatch.exerciciosExtras.model.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciciosStreams {

    public void encontraPares(){
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void toUpperCase(){
        List<String> palavras = Arrays.asList("java", "stream", "lambda");
        palavras.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void filtrarImparVezesDois() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        numeros.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void removerDuplicados(){
        List<String> palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        palavras.stream()
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void extraiNumerosPrimosEOrdenaCrescente(){
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );

        listaDeNumeros.stream()
                .flatMap(n -> n.stream()
                        .filter(num -> avaliaSeNumeroEhPrimoRecursivo(num))
                        .sorted(Comparator.comparing(Integer::intValue))
                )
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void pessoaMaiorDe18AnosOrdemAlfabetica(){
        ArrayList<Pessoa> pessoas = new ArrayList<>(Arrays.asList(
                new Pessoa("João", 20),
                new Pessoa("Maria", 17),
                new Pessoa("Pedro", 22)
        ));

        pessoas.stream()
                .filter(p -> p.getIdade() >= 18)
                .map(Pessoa::getNome)
                .sorted()
                .forEach(System.out::println);
    }

    public void filtrarProdutosEletronicosMaisBaratos() {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        List<Produto> maisBaratos = produtos.stream()
                .filter(p -> p.getCategoria() == "Eletrônicos" && p.getPreco() < 1000)
                .sorted(Comparator.comparingDouble(Produto::getPreco))
                .limit(3)
                .collect(Collectors.toList());

        maisBaratos.forEach(System.out::println);
    }

    private boolean avaliaSeNumeroEhPrimoRecursivo(int numero) {
        return avaliaPrimoRecursivo(numero, 3);
    }

    private boolean avaliaPrimoRecursivo(int numero, int divisor) {
        if (numero <= 2) {
            return true;
        }
        if (numero % 2 == 0) {
            return false;
        }

        if (numero % divisor == 0) {
            return false;
        }

        if (divisor * divisor > numero) {
            return true;
        }

        return avaliaPrimoRecursivo(numero, divisor + 2);
    }
}
