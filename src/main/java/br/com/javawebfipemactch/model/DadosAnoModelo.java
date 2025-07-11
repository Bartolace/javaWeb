package br.com.javawebfipemactch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAnoModelo(@JsonAlias("codigo") String codigo,
                             @JsonAlias("nome") String nome)
                           {
}
