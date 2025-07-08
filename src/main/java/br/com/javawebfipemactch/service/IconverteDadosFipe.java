package br.com.javawebfipemactch.service;


import com.fasterxml.jackson.core.type.TypeReference;

public interface IconverteDadosFipe {
    <T> T converter(String json, Class<T> classe);
    <T> T converterLista(String json, TypeReference<T> typeReference);
}

