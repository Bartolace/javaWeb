package br.com.alura.javawebscreenmatch.service;

public interface IconverteDados {
    <T> T converter(String json, Class<T> classe);

}
