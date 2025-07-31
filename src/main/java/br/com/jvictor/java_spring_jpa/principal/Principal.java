package br.com.jvictor.java_spring_jpa.principal;

import br.com.jvictor.java_spring_jpa.model.DadosSerie;
import br.com.jvictor.java_spring_jpa.model.DadosTemporada;
import br.com.jvictor.java_spring_jpa.service.ConsumoApi;
import br.com.jvictor.java_spring_jpa.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner lerDados = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


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

        switch (opcao){
            case 1 :
                buscarSerieWeb();
                break;
            case 2 :
                buscarEpisodioPorSerie();
                break;
            case 0 :
                System.out.println("Saindo...");
            default:
                System.out.println("Opção inválida!");
        }

    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = lerDados.nextLine();
        var json = consumo.obterDados(ENDERECO_URL + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO_URL + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
