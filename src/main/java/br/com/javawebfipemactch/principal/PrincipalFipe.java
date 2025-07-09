package br.com.javawebfipemactch.principal;

import br.com.alura.javawebscreenmatch.service.ConsumoApi;
import br.com.alura.javawebscreenmatch.service.ConverteDados;
import br.com.javawebfipemactch.model.DadosMarca;
import br.com.javawebfipemactch.model.DadosModelo;
import br.com.javawebfipemactch.model.DadosModeloWrapper;
import br.com.javawebfipemactch.service.ConsumoApiFipe;
import br.com.javawebfipemactch.service.ConverteDadosFipe;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PrincipalFipe {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApiFipe consumo = new ConsumoApiFipe();
    private ConverteDadosFipe conversor = new ConverteDadosFipe();

    private static final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private static final String MARCAS = "/marcas";
    private static final String MODELOS = "/modelos";
    private static final String ANOS = "/anos";


    public void exibeMenu(){
        System.out.println("Bem-vindo ao sistema de consulta de veículos da Tabela Fipe!");
        System.out.println("Digite o tipo de veículo: Carros, Motos ou Caminhões");

        String tipoVeiculo = leitura.nextLine().toLowerCase();

        String json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + MARCAS);

        ArrayList<DadosMarca> marcas = conversor.converterLista(json, new TypeReference<ArrayList<DadosMarca>>() {});

        System.out.println("=================================================");
        marcas.stream()
                .sorted(Comparator.comparing(m -> m.nome().toLowerCase()))
                .forEach(System.out::println);

        System.out.println("Escolha um código de marca disponível acima: ");
        String codigoMarca = leitura.nextLine();
        json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + MARCAS + "/" + codigoMarca + MODELOS);

        DadosModeloWrapper modeloWrapper = conversor.converter(json, DadosModeloWrapper.class);
        List<DadosModelo> modelos = modeloWrapper.modelos();

        System.out.println("=================================================");
        //capturar entrada do nome do modelo, filtrar e exibir modelos
        modelos.stream()
                .sorted(Comparator.comparing(m -> m.nome().toLowerCase()))
                .forEach(System.out::println);



    }
}