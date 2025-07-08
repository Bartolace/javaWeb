package br.com.javawebfipemactch.service;

public interface IconverteDadosFipe {

    <T> T converter(String json, Class<T> classe);
}
