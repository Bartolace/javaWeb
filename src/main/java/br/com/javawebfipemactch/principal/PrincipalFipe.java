package br.com.javawebfipemactch.principal;

import br.com.javawebfipemactch.model.*;
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

        ArrayList<DadosMarca> marcas = buscaMarcas(tipoVeiculo);
        if (marcas.isEmpty()) {
            System.out.println("Nenhuma marca encontrada para o tipo de veículo informado.");
            return;
        }

        System.out.println("===================== Marcas =====================");
        marcas.stream()
                .sorted(Comparator.comparing(m -> m.nome().toLowerCase()))
                .forEach(System.out::println);

        System.out.println("Escolha um código de marca disponível acima: ");
        String codigoMarca = leitura.nextLine();

        List<DadosModelo> modelos = buscaModelos(tipoVeiculo, codigoMarca);
        if (modelos.isEmpty()) {
            System.out.println("Nenhum modelo encontrado com o código informado.");
            return;
        }

        System.out.println("===================== Modelos =====================");
        modelos.stream()
                .sorted(Comparator.comparing(m -> m.nome().toLowerCase()))
                .forEach(System.out::println);

        System.out.println("Digite o nome do modelo que deseja consultar: ");
        String modeloEscolhido = leitura.nextLine().toLowerCase();

        List<DadosModelo> modelosFiltrados = modelos.stream()
                .filter(m -> m.nome().toLowerCase().contains(modeloEscolhido))
                .sorted(Comparator.comparing(DadosModelo::nome))
                .toList();

        System.out.println("==================== Modelos Filtrados ====================");
        if (modelosFiltrados.isEmpty()) {
            System.out.println("Nenhum modelo encontrado com o nome informado.");
            return;
        }
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo que deseja consultar: ");
        String codigoModelo = leitura.nextLine();
        ArrayList<DadosAnoModelo> dadosAnoModelos = buscaAnosModelos(tipoVeiculo, codigoMarca, codigoModelo);
        if (dadosAnoModelos.isEmpty()) {
            System.out.println("Nenhum ano modelo encontrado com o código informado.");
            return;
        }

        System.out.println("==================== Avaliações ====================");
        buscaAvaliacoes(dadosAnoModelos, tipoVeiculo, codigoMarca, codigoModelo)
                .stream()
                .sorted(Comparator.comparing(DadosAvaliacao::anoModelo))
                .forEach(a -> System.out.println(
                    a.modelo() + "  ano: " + a.anoModelo() + "  valor: " + a.valor() + "  combustível: " + a.combustivel(
                )));
    }

    private ArrayList<DadosMarca> buscaMarcas(String tipoVeiculo){
        String json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + MARCAS);
        if(json.contains("error")) {
            return new ArrayList<>();
        }
        return conversor.converterLista(json, new TypeReference<ArrayList<DadosMarca>>() {});
    }

    private boolean validarIOTipoVeiculo(String tipo) {
        List<String> tiposValidos = List.of("carros", "motos", "caminhoes");
        if (!tiposValidos.contains(tipo)) {
            return false;
        }
        return true;
    }

    private ArrayList<DadosAvaliacao> buscaAvaliacoes(ArrayList<DadosAnoModelo> dadosAnoModelos, String tipoVeiculo, String codigoMarca, String codigoModelo) {
        ArrayList<DadosAvaliacao> avaliacoes = new ArrayList<>();
        for (DadosAnoModelo dadoAnoModelo: dadosAnoModelos){
            var json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + MARCAS + "/" + codigoMarca + MODELOS + "/" + codigoModelo + ANOS + "/" + dadoAnoModelo.codigo());

            DadosAvaliacao avaliacao = conversor.converter(json, DadosAvaliacao.class);
            avaliacoes.add(avaliacao);
        }
        return avaliacoes;
    }

    private List<DadosModelo> buscaModelos(String tipoVeiculo, String codigoMarca){
        var json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + MARCAS + "/" + codigoMarca + MODELOS);
        if(json.contains("error")) {
            return List.of();
        }

        DadosModeloWrapper modeloWrapper = conversor.converter(json, DadosModeloWrapper.class);
        List<DadosModelo> modelos = modeloWrapper.modelos();
        return modelos;
    }

    private boolean validarBuscaModelos(DadosModeloWrapper modeloWrapper){
        if(modeloWrapper.modelos() == null) {
            return false;
        }
        return true;
    }

    private ArrayList<DadosAnoModelo> buscaAnosModelos(String tipoVeiculo, String codigoMarca, String codigoModelo){
        var json = consumo.obterDadosFipe(ENDERECO + tipoVeiculo + MARCAS + "/" + codigoMarca + MODELOS + "/" + codigoModelo + ANOS);
        if(json.contains("error")) return new ArrayList<>();

        ArrayList<DadosAnoModelo> dadosAnoModelos = conversor.converterLista(json, new TypeReference<ArrayList<DadosAnoModelo>>() {});
        return dadosAnoModelos;
    }
}























