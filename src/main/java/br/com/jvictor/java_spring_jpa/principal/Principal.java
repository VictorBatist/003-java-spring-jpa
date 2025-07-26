package br.com.jvictor.java_spring_jpa.principal;

import java.util.Scanner;

public class Principal {
    private Scanner lerDados = new Scanner(System.in);

    private final String ENDERECO_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "";

    public void exibeMenu() {
        var menu = """
                |----------------------|
                | 1 - Buscar séries    |
                | 2 - Buscar Episódios |
                |                      |
                | 0 - Sair             |
                |----------------------|
                """;

        System.out.println(menu);

        var opcao = lerDados.nextInt();
        lerDados.nextLine();
    }
}
