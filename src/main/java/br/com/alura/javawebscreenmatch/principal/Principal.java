package br.com.alura.javawebscreenmatch.principal;

import br.com.alura.javawebscreenmatch.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    //"https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c"
    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();
    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.printf("Digite o nome da séria para buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        System.out.printf(json);

    }
}
