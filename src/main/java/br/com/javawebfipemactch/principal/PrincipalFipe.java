package br.com.javawebfipemactch.principal;

import br.com.alura.javawebscreenmatch.service.ConsumoApi;
import br.com.alura.javawebscreenmatch.service.ConverteDados;
import br.com.javawebfipemactch.model.DadosMarca;
import br.com.javawebfipemactch.service.ConsumoApiFipe;
import br.com.javawebfipemactch.service.ConverteDadosFipe;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalFipe {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApiFipe consumo = new ConsumoApiFipe();
    private ConverteDadosFipe conversor = new ConverteDadosFipe();

    private static final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";


    public void exibeMenu(){
        System.out.println("Bem-vindo ao sistema de consulta de veículos da Tabela Fipe!");
        System.out.println("Digite o tipo de veículo: Carros, Motos ou Caminhões");

        String tipoVeiculo = leitura.nextLine().toLowerCase();

        String json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + "/marcas");

        ArrayList<DadosMarca> marcas = conversor.converterLista(json, new TypeReference<ArrayList<DadosMarca>>() {});

        System.out.println(marcas);
    }
}

