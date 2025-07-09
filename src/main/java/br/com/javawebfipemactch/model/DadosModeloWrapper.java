package br.com.javawebfipemactch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModeloWrapper(@JsonAlias("modelos") List<DadosModelo> modelos) {
}
