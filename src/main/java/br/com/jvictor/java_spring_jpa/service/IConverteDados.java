package br.com.jvictor.java_spring_jpa.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
