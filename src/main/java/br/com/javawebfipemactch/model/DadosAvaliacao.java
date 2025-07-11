package br.com.javawebfipemactch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAvaliacao(@JsonAlias("TipoVeiculo") Integer tipoVeiculo,
                                @JsonAlias("Valor") String valor,
                                @JsonAlias("Marca") String marca,
                                @JsonAlias("Modelo") String modelo,
                                @JsonAlias("AnoModelo") Integer anoModelo,
                                @JsonAlias("Combustivel") String combustivel,
                                @JsonAlias("CodigoFipe") String codigoFipe,
                                @JsonAlias("MesReferencia") String mesReferencia,
                                @JsonAlias("SiglaCombustivel") String siglaCombustivel ) {
}
